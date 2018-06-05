package com.glowstick.engine.graphics;

import com.glowstick.engine.service.Cacheable;

import static org.lwjgl.opengl.GL11.*;

public class Texture extends Cacheable {
    private int id;

    public Texture(String name) {
        super(name);
        this.id = glGenTextures();
        this.bind();
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, this.id);
    }
}
