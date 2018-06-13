package com.glowstick.engine.builders.entity;

import com.glowstick.engine.builders.NamedEntityBuilder;
import com.glowstick.engine.builders.TextureBuilder;
import com.glowstick.engine.cache.ModelCache;
import com.glowstick.engine.cache.ShaderCache;
import com.glowstick.engine.cache.TextureCache;
import com.glowstick.engine.game.entity.FboEntity;
import com.glowstick.engine.graphics.Texture;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glDrawBuffers;
import static org.lwjgl.opengl.GL30.*;

@Component
@Getter
public class FboEntityBuilder extends NamedEntityBuilder<FboEntity> {
    private String name = "fbo";

    @Autowired
    private TextureBuilder textureBuilder;

    @Override
    public FboEntity build(ModelCache modelCache, ShaderCache shaderCache, TextureCache textureCache) throws Exception {
        int frameBuffer = glGenFramebuffers();
        glBindFramebuffer(GL_FRAMEBUFFER, frameBuffer);

        Texture depthTexture = this.textureBuilder.buildDepth("depth", 800, 600);
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_TEXTURE_2D, depthTexture.getId(), 0);

        Texture colorTexture = this.textureBuilder.buildFloat("color", 800, 600);
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, colorTexture.getId(), 0);

        Texture positionTexture = this.textureBuilder.buildFloat("position", 800, 600);
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT1, GL_TEXTURE_2D, positionTexture.getId(), 0);

        Texture normalTexture = this.textureBuilder.buildFloat("normal", 800, 600);
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT2, GL_TEXTURE_2D, normalTexture.getId(), 0);

        glDrawBuffers(new int[] {GL_COLOR_ATTACHMENT0, GL_COLOR_ATTACHMENT1, GL_COLOR_ATTACHMENT2});

        System.out.println(glCheckFramebufferStatus(GL_FRAMEBUFFER));

        glBindFramebuffer(GL_FRAMEBUFFER, 0);

        return new FboEntity(modelCache, shaderCache, textureCache, frameBuffer, depthTexture, positionTexture, normalTexture, colorTexture);
    }
}
