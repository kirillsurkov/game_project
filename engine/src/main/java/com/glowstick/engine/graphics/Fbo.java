package com.glowstick.engine.graphics;

import com.glowstick.engine.builders.EntityBuilder;
import com.glowstick.engine.builders.TextureBuilder;
import com.glowstick.engine.game.camera.Camera;
import com.glowstick.engine.game.entity.FboEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL30.*;

@Component
public class Fbo {
    private FboEntity fboEntity;

    @Autowired
    public Fbo(EntityBuilder entityBuilder, TextureBuilder textureBuilder) throws Exception {
        this.fboEntity = (FboEntity)entityBuilder.build("fbo");
    }

    public void bind() {
        glBindFramebuffer(GL_FRAMEBUFFER, this.fboEntity.getFrameBuffer());
    }

    public void unbind() {
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }

    public void draw(Camera camera) {
        glActiveTexture(GL_TEXTURE0);
        this.fboEntity.getColorTexture().bind();
        glActiveTexture(GL_TEXTURE1);
        this.fboEntity.getPositionTexture().bind();
        glActiveTexture(GL_TEXTURE2);
        this.fboEntity.getNormalTexture().bind();
        glActiveTexture(GL_TEXTURE3);
        this.fboEntity.getGlowTexture().bind();
        glActiveTexture(GL_TEXTURE4);
        this.fboEntity.getDepthTexture().bind();
        this.fboEntity.draw(camera);
    }
}
