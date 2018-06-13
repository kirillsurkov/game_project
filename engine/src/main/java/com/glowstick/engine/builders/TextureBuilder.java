package com.glowstick.engine.builders;

import com.glowstick.engine.graphics.Texture;
import org.apache.commons.io.IOUtils;
import org.lwjgl.system.MemoryStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.GL_DEPTH_COMPONENT32F;
import static org.lwjgl.opengl.GL30.GL_RGB16F;
import static org.lwjgl.stb.STBImage.stbi_load_from_memory;
import static org.lwjgl.stb.STBImage.stbi_set_flip_vertically_on_load;

@Component
public class TextureBuilder implements Builder<Texture> {
    @Autowired
    private ResourceLoader resourceLoader;

    public Texture build(String name, int width, int height, int internalFormat, int format, int type, ByteBuffer pixels) {
        int texture = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texture);
        if (pixels == null) {
            glTexImage2D(GL_TEXTURE_2D, 0, internalFormat, width, height, 0, format, type, 0);
        } else {
            glTexImage2D(GL_TEXTURE_2D, 0, internalFormat, width, height, 0, format, type, pixels);
        }
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        return new Texture(name, texture);
    }

    public Texture buildDepth(String name, int width, int height) {
        return this.build(name, width, height, GL_DEPTH_COMPONENT32F, GL_DEPTH_COMPONENT, GL_FLOAT, null);
    }

    public Texture buildFloat(String name, int width, int height) {
        return this.build(name, width, height, GL_RGB16F, GL_RGB, GL_FLOAT, null);
    }

    @Override
    public Texture build(String name) throws Exception {
        Resource resource = this.resourceLoader.getResource(name);
        InputStream inputStream = resource.getInputStream();
        ByteBuffer data = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
        ByteBuffer pixels;
        int width, height;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);
            pixels = stbi_load_from_memory(data, w, h, comp, 4);
            width = w.get();
            height = h.get();
        }
        return this.build(name, width, height, GL_RGBA, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
    }
}
