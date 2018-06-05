package com.glowstick.engine.graphics;

import com.glowstick.engine.extension.Cacheable;
import com.glowstick.engine.game.Camera;
import com.glowstick.engine.game.Entity;
import lombok.Getter;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

public class Model implements Cacheable {
    @Getter
    private final String name;
    private final int count;
    private final int vbo;

    public Model(String name, float[] vertices, int count) {
        this.name = name;
        this.count = count;
        this.vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, this.vbo);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
    }

    public void draw(Shader shader, Camera camera, Entity entity) {
        glBindBuffer(GL_ARRAY_BUFFER, this.vbo);
        shader.bind(camera, entity);
        glDrawArrays(GL_TRIANGLES, 0, this.count);
    }
}
