package com.glowstick.engine.graphics;

import com.glowstick.engine.extension.Cacheable;
import lombok.Getter;

import static org.lwjgl.opengl.GL11.*;

public class Texture implements Cacheable {
    @Getter
    private String name;
    private int id;

    public Texture(String name) {
        this.name = name;
        this.id = glGenTextures();
        this.bind();
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, this.id);
    }
}
