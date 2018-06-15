package com.glowstick.engine.graphics;

import com.glowstick.engine.builders.TextureBuilder;
import com.glowstick.engine.cache.ModelCache;
import com.glowstick.engine.cache.ShaderCache;
import com.glowstick.engine.game.camera.Camera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.glDrawBuffers;
import static org.lwjgl.opengl.GL30.*;

@Component
public class Fbo {
    private Model plane;

    private Shader fboShader;
    private Shader blurShader;
    private Shader fxaaShader;

    private int fboFXAA;
    private int fboBlur;
    private int fbo;

    private Texture colorTextureFXAA;
    private Texture glowTextureFXAA;

    private Texture glowTextureBlur;

    private Texture depthTexture;
    private Texture colorTexture;
    private Texture positionTexture;
    private Texture normalTexture;
    private Texture glowTexture;

    @Autowired
    public Fbo(ModelCache modelCache, ShaderCache shaderCache, TextureBuilder textureBuilder) throws Exception {
        this.plane = modelCache.get("plane");

        this.fboShader = shaderCache.get("fbo");
        this.blurShader = shaderCache.get("blur");
        this.fxaaShader = shaderCache.get("fxaa");

        // TODO: move to service
        this.fboFXAA = glGenFramebuffers();
        glBindFramebuffer(GL_FRAMEBUFFER, this.fboFXAA);

        this.colorTextureFXAA = textureBuilder.buildFloat("colorFXAA", 800, 600);
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, colorTextureFXAA.getId(), 0);

        this.glowTextureFXAA = textureBuilder.buildFloat("glowFXAA", 800, 600);
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT1, GL_TEXTURE_2D, glowTextureFXAA.getId(), 0);

        glDrawBuffers(new int[] {GL_COLOR_ATTACHMENT0, GL_COLOR_ATTACHMENT1});

        this.fboBlur = glGenFramebuffers();
        glBindFramebuffer(GL_FRAMEBUFFER, this.fboBlur);

        this.glowTextureBlur = textureBuilder.buildFloat("glowBlur", 800, 600);
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, glowTextureBlur.getId(), 0);

        glDrawBuffers(new int[] {GL_COLOR_ATTACHMENT0});

        this.fbo = glGenFramebuffers();
        glBindFramebuffer(GL_FRAMEBUFFER, this.fbo);

        this.depthTexture = textureBuilder.buildDepth("depth", 800, 600);
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_TEXTURE_2D, depthTexture.getId(), 0);

        this.colorTexture = textureBuilder.buildFloat("color", 800, 600);
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, colorTexture.getId(), 0);

        this.positionTexture = textureBuilder.buildFloat("position", 800, 600);
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT1, GL_TEXTURE_2D, positionTexture.getId(), 0);

        this.normalTexture = textureBuilder.buildFloat("normal", 800, 600);
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT2, GL_TEXTURE_2D, normalTexture.getId(), 0);

        this.glowTexture = textureBuilder.buildFloat("glow", 800, 600);
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT3, GL_TEXTURE_2D, glowTexture.getId(), 0);

        glDrawBuffers(new int[] {GL_COLOR_ATTACHMENT0, GL_COLOR_ATTACHMENT1, GL_COLOR_ATTACHMENT2, GL_COLOR_ATTACHMENT3});

        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }

    public void bind() {
        glBindFramebuffer(GL_FRAMEBUFFER, this.fbo);
    }

    public void unbind() {
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }

    public void draw(Camera camera) {
        glBindFramebuffer(GL_FRAMEBUFFER, this.fboFXAA);
        glActiveTexture(GL_TEXTURE0);
        this.colorTexture.bind();
        glActiveTexture(GL_TEXTURE1);
        this.glowTexture.bind();
        this.plane.draw(this.fxaaShader, camera, null);

        glBindFramebuffer(GL_FRAMEBUFFER, this.fboBlur);
        glActiveTexture(GL_TEXTURE0);
        this.colorTextureFXAA.bind();
        glActiveTexture(GL_TEXTURE1);
        this.glowTextureFXAA.bind();
        this.plane.draw(this.blurShader, camera, null);

        glBindFramebuffer(GL_FRAMEBUFFER, 0);
        glActiveTexture(GL_TEXTURE0);
        this.colorTextureFXAA.bind();
        glActiveTexture(GL_TEXTURE1);
        this.positionTexture.bind();
        glActiveTexture(GL_TEXTURE2);
        this.normalTexture.bind();
        glActiveTexture(GL_TEXTURE3);
        this.glowTexture.bind();
        glActiveTexture(GL_TEXTURE4);
        this.glowTextureBlur.bind();
        glActiveTexture(GL_TEXTURE5);
        this.depthTexture.bind();
        this.plane.draw(this.fboShader, camera, null);
    }
}
