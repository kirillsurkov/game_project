package com.glowstick.engine.graphics;

import com.glowstick.engine.builders.TextureBuilder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL20.glDrawBuffers;
import static org.lwjgl.opengl.GL30.*;

@Component
@Getter
public class Gbo {
    private final int gbo;
    private Texture depthTexture;
    private Texture colorTexture;
    private Texture positionTexture;
    private Texture normalTexture;

    @Autowired
    public Gbo(Window window, TextureBuilder textureBuilder) {
        int width = window.getWidth();
        int height = window.getHeight();

        this.gbo = glGenFramebuffers();
        glBindFramebuffer(GL_FRAMEBUFFER, this.gbo);

        this.depthTexture = textureBuilder.buildDepth("depth", width, height);
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_TEXTURE_2D, depthTexture.getId(), 0);

        this.colorTexture = textureBuilder.buildFloat("color", width, height);
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, colorTexture.getId(), 0);

        this.positionTexture = textureBuilder.buildFloat("position", width, height);
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT1, GL_TEXTURE_2D, positionTexture.getId(), 0);

        this.normalTexture = textureBuilder.buildFloat("normal", width, height);
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT2, GL_TEXTURE_2D, normalTexture.getId(), 0);

        glDrawBuffers(new int[] {GL_COLOR_ATTACHMENT0, GL_COLOR_ATTACHMENT1, GL_COLOR_ATTACHMENT2});

        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }

    public void bind() {
        glBindFramebuffer(GL_FRAMEBUFFER, this.gbo);
    }

    public void unbind() {
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }
}
