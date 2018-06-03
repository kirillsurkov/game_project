package com.glowstick.engine.graphics;

import com.glowstick.engine.service.Cacheable;
import com.glowstick.engine.service.Entity;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public abstract class Shader extends Cacheable {
    private final int program;
    private final int vao;

    public Shader(String name, int program) {
        super(name);
        this.program = program;
        this.vao = glGenVertexArrays();
        this.use();
        this.linkAttributes();
    }

    abstract protected void linkAttributes();
    abstract protected void linkUniforms(Entity entity);

    private void use() {
        glUseProgram(this.program);
        glBindVertexArray(this.vao);
    }

    protected int getAttribLocation(String attrib) {
        return glGetAttribLocation(this.program, attrib);
    }

    protected int getUniformLocation(String uniform) {
        return glGetUniformLocation(this.program, uniform);
    }

    public void bind(Entity entity) {
        this.linkUniforms(entity);
        this.use();
    }
}
