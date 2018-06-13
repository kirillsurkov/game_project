package com.glowstick.engine.graphics;

import com.glowstick.engine.extension.Cacheable;
import lombok.Getter;

import static org.lwjgl.opengl.GL11.*;

@Getter
public class Texture implements Cacheable {
    private String name;
    private int id;

    public Texture(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, this.id);
    }
}
