package com.glowstick.engine.graphics;

import com.glowstick.engine.geometry.Vertex;
import com.glowstick.engine.caches.ShaderCache;
import com.glowstick.engine.service.Cacheable;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Model extends Cacheable {
    private List<Vertex> vertices;

    private Shader shader;

    private final int vao;
    private final int vbo;

    public Model(String name, Shader shader, List<Vertex> vertices) {
        super(name);

        this.vertices = vertices;
        this.shader = shader;

        this.vao = glGenVertexArrays();
        this.vbo = glGenBuffers();

        List<Double> rawVertices = new ArrayList<>();
        vertices.forEach(vertex -> {
            rawVertices.add(vertex.getCoodrs().getX());
            rawVertices.add(vertex.getCoodrs().getY());
        });

        glBindBuffer(GL_ARRAY_BUFFER, this.vbo);
        glBufferData(GL_ARRAY_BUFFER,
                rawVertices.stream().mapToDouble(Double::doubleValue).toArray(),
                GL_STATIC_DRAW
        );

        glBindVertexArray(this.vao);
        shader.link();
    }

    public void draw() {
        this.shader.use();
        glBindBuffer(GL_ARRAY_BUFFER, this.vbo);
        glBindVertexArray(this.vao);
        glDrawArrays(GL_TRIANGLES, 0, this.vertices.size());
    }
}
