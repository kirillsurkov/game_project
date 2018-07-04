package com.glowstick.engine.builders;

import ar.com.hjg.pngj.*;
import com.glowstick.engine.graphics.Texture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.EXTTextureFilterAnisotropic.GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT;
import static org.lwjgl.opengl.EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.GL_DEPTH_COMPONENT32F;
import static org.lwjgl.opengl.GL30.GL_RGBA16F;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

@Component
public class TextureBuilder implements Builder<Texture> {
    @Autowired
    private ResourceLoader resourceLoader;

    public Texture build(String name, int width, int height, int internalFormat, int format, int type, boolean mipmaps, boolean anisotropy, ByteBuffer pixels) {
        int texture = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texture);
        if (pixels == null) {
            glTexImage2D(GL_TEXTURE_2D, 0, internalFormat, width, height, 0, format, type, 0);
        } else {
            glTexImage2D(GL_TEXTURE_2D, 0, internalFormat, width, height, 0, format, type, pixels);
        }
        if (mipmaps) {
            glGenerateMipmap(GL_TEXTURE_2D);
        }

        if (anisotropy) {
            float ratio = glGetFloat(GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT);
            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAX_ANISOTROPY_EXT, ratio);
        }
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, mipmaps ? GL_LINEAR_MIPMAP_LINEAR : GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, mipmaps ? GL_LINEAR_MIPMAP_LINEAR : GL_LINEAR);
        return new Texture(name, texture);
    }

    public Texture buildDepth(String name, int width, int height) {
        return this.build(name, width, height, GL_DEPTH_COMPONENT32F, GL_DEPTH_COMPONENT, GL_FLOAT, false, false, null);
    }

    public Texture buildFloat(String name, int width, int height) {
        return this.build(name, width, height, GL_RGBA16F, GL_RGBA, GL_FLOAT, false, false, null);
    }

    @Override
    public Texture build(String name) throws Exception {
        Resource resource = this.resourceLoader.getResource("classpath:textures/" + name + "/texture.png");
        InputStream inputStream = resource.getInputStream();
        PngReaderByte reader = new PngReaderByte(inputStream);
        int width = reader.imgInfo.cols;
        int height = reader.imgInfo.rows;
        int channels = reader.imgInfo.channels;
        ByteBuffer pixels = ByteBuffer.allocateDirect(width * height * channels);
        int position = width * height * channels;
        while (reader.hasMoreRows()) {
            byte[] line = reader.readRowByte().getScanline();
            pixels.position(position -= width * channels);
            pixels.put(line);
        }
        pixels.position(0);
        return this.build(name, width, height, GL_RGBA, GL_RGBA, GL_UNSIGNED_BYTE, true, true, pixels);
    }
}
