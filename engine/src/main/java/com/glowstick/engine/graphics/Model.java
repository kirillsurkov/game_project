package com.glowstick.engine.graphics;

import com.glowstick.engine.extension.Cacheable;
import com.glowstick.engine.game.camera.Camera;
import com.glowstick.engine.game.Entity;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Model implements Cacheable {
    @Getter
    private final String name;
    @Getter
    private final int count;
    private final int vao;
    private final int vbo;
    private Map<Shader, Boolean> isReady;

    public Model(String name, int vao, int vbo, int count) {
        this.name = name;
        this.vao = vao;
        this.vbo = vbo;
        this.count = count;
        this.isReady = new HashMap<>();
    }

    public void draw(Shader shader, Camera camera, Entity entity) {
        glBindBuffer(GL_ARRAY_BUFFER, this.vbo);
        glBindVertexArray(this.vao);
        if (this.isReady.get(shader) == null) {
            this.isReady.put(shader, true);
            shader.linkAttributes();
        }
        shader.bind(camera, entity);
        glDrawArrays(GL_TRIANGLES, 0, this.count);
        glBindVertexArray(0);
    }
}
