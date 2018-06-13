package com.glowstick.engine.graphics;

import com.glowstick.engine.builders.EntityBuilder;
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
    public Fbo(EntityBuilder entityBuilder) throws Exception {
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
        glBindTexture(GL_TEXTURE_2D, this.fboEntity.getColorTexture());
        glActiveTexture(GL_TEXTURE1);
        glBindTexture(GL_TEXTURE_2D, this.fboEntity.getPositionTexture());
        glActiveTexture(GL_TEXTURE2);
        glBindTexture(GL_TEXTURE_2D, this.fboEntity.getNormalTexture());
        glActiveTexture(GL_TEXTURE3);
        glBindTexture(GL_TEXTURE_2D, this.fboEntity.getDepthTexture());
        this.fboEntity.draw(camera);
    }
}
