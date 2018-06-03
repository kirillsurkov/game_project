package com.glowstick.engine.graphics;

import com.glowstick.engine.service.Cacheable;
import com.glowstick.engine.service.Entity;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

public class Model extends Cacheable {
    private final int count;
    private final int vbo;

    public Model(String name, float[] vertices, int count) {
        super(name);

        this.count = count;

        this.vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, this.vbo);
        glBufferData(GL_ARRAY_BUFFER,
                vertices,
                GL_STATIC_DRAW
        );
    }

    public void draw(Shader shader, Entity entity) {
        glBindBuffer(GL_ARRAY_BUFFER, this.vbo);
        shader.bind(entity);
        glDrawArrays(GL_TRIANGLES, 0, this.count);
    }
}
