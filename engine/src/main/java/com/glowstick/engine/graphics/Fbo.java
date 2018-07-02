package com.glowstick.engine.graphics;

import com.glowstick.engine.builders.TextureBuilder;
import com.glowstick.engine.cache.ModelCache;
import com.glowstick.engine.cache.ShaderCache;
import com.glowstick.engine.game.InputListener;
import com.glowstick.engine.game.camera.Camera;
import com.glowstick.engine.graphics.shader.BlurShader;
import com.glowstick.engine.graphics.shader.CopyShader;
import com.glowstick.engine.graphics.shader.FXAAShader;
import com.glowstick.engine.graphics.shader.MotionBlurShader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_F;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_G;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.glDrawBuffers;
import static org.lwjgl.opengl.GL30.*;

@Component
public class Fbo {
    @Autowired
    private InputListener inputListener;

    private Model plane;

    private Shader fboShader;
    private CopyShader copyShader;
    private BlurShader blurShader;
    private FXAAShader fxaaShader;
    private MotionBlurShader motionBlurShader;

    private int fbo;

    private Texture tmpTexture;
    private Texture fxaaTexture;
    private Texture blurTexture;
    private Texture motionBlurTexture;
    private Texture fboTexture;

    private List<Texture> frames;

    private int width;
    private int height;

    @Autowired
    public Fbo(ModelCache modelCache, ShaderCache shaderCache, TextureBuilder textureBuilder, Window window) throws Exception {
        this.width = window.getWidth();
        this.height = window.getHeight();

        this.plane = modelCache.get("plane");

        this.fboShader = shaderCache.get("fbo");
        this.copyShader = (CopyShader)shaderCache.get("copy");
        this.blurShader = (BlurShader)shaderCache.get("blur");
        this.fxaaShader = (FXAAShader)shaderCache.get("fxaa");
        this.motionBlurShader = (MotionBlurShader)shaderCache.get("motionblur");

        this.fbo = glGenFramebuffers();
        glBindFramebuffer(GL_FRAMEBUFFER, this.fbo);
        glDrawBuffers(new int[] {GL_COLOR_ATTACHMENT0});
        glBindFramebuffer(GL_FRAMEBUFFER, 0);

        this.tmpTexture = textureBuilder.buildFloat("tmp", width, height);
        this.fxaaTexture = textureBuilder.buildFloat("fxaa", width, height);
        this.blurTexture = textureBuilder.buildFloat("blur", width, height);
        this.motionBlurTexture = textureBuilder.buildFloat("motionBlur", width, height);
        this.fboTexture = textureBuilder.buildFloat("fbo", width, height);

        this.frames = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            this.frames.add(textureBuilder.buildFloat("prevFrame" + i, this.width, this.height));
        }
    }

    private void blur(Texture textureIn, Texture textureOut, Camera camera) {
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, this.tmpTexture.getId(), 0);
        glActiveTexture(GL_TEXTURE0);
        textureIn.bind();
        this.blurShader.setFirstPass(true);
        this.plane.draw(this.blurShader, camera, null);
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, textureOut.getId(), 0);
        this.tmpTexture.bind();
        this.blurShader.setFirstPass(false);
        this.plane.draw(this.blurShader, camera, null);
    }

    private void fxaa(Texture textureIn, Texture textureOut, int samples, Camera camera) {
        glActiveTexture(GL_TEXTURE0);
        textureIn.bind();
        Texture[] tex = new Texture[]{this.tmpTexture, textureOut};
        this.fxaaShader.setEnabled(true);
        int offset = samples % 2;
        for (int i = 0; i < samples; i++) {
            glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, tex[(i + offset) % 2].getId(), 0);
            this.plane.draw(this.fxaaShader, camera, null);
            tex[(i + offset) % 2].bind();
        }
    }

    private void motionBlur(Texture textureIn, Texture textureOut, Camera camera) {
        Texture frame = this.frames.remove(this.frames.size()-1);
        this.frames.add(0, frame);
        glActiveTexture(GL_TEXTURE0);
        textureIn.bind();
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, frame.getId(), 0);
        this.plane.draw(this.copyShader, camera, null);

        for (int i = 0; i < this.frames.size(); i++) {
            glActiveTexture(GL_TEXTURE0 + i);
            this.frames.get(i).bind();
        }
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, textureOut.getId(), 0);
        this.plane.draw(this.motionBlurShader, camera, null);
    }

    public void compose(Gbo gbo, Texture colorTexture, Texture glowTexture, Texture outTexture, Camera camera) {
        glActiveTexture(GL_TEXTURE0);
        colorTexture.bind();
        glActiveTexture(GL_TEXTURE1);
        gbo.getPositionTexture().bind();
        glActiveTexture(GL_TEXTURE2);
        gbo.getNormalTexture().bind();
        glActiveTexture(GL_TEXTURE3);
        glowTexture.bind();
        glActiveTexture(GL_TEXTURE4);
        gbo.getDepthTexture().bind();
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, outTexture.getId(), 0);
        this.plane.draw(this.fboShader, camera, null);
    }

    public void draw(Gbo gbo, Camera camera) {
        glBindFramebuffer(GL_FRAMEBUFFER, this.fbo);

        Texture out = this.fboTexture;

        this.fxaa(gbo.getColorTexture(), this.fxaaTexture, 4, camera);
        this.blur(this.fxaaTexture, this.blurTexture, camera);
        this.motionBlur(this.fxaaTexture, this.motionBlurTexture, camera);
        this.compose(gbo, this.motionBlurTexture, this.blurTexture, this.fboTexture, camera);

        if (this.inputListener.isKeyDown(GLFW_KEY_F)) {
            out = this.motionBlurTexture;
        }

        glBindFramebuffer(GL_FRAMEBUFFER, 0);
        glActiveTexture(GL_TEXTURE0);
        out.bind();
        this.plane.draw(this.copyShader, camera, null);
    }
}
