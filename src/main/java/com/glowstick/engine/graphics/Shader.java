package com.glowstick.engine.graphics;

import com.glowstick.engine.game.Camera;
import com.glowstick.engine.extension.Cacheable;
import com.glowstick.engine.game.Entity;
import lombok.Getter;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public abstract class Shader implements Cacheable {
    @Getter
    private final String name;
    private final int program;
    private final int vao;

    public Shader(String name, int program, int vao) {
        this.name = name;
        this.program = program;
        this.vao = vao;
    }

    protected void linkVertexAttributes() {
        int posAttrib = this.getAttribLocation("position");
        glVertexAttribPointer(posAttrib, 3, GL_FLOAT, false, 32, 0);
        glEnableVertexAttribArray(posAttrib);
        int normalAttrib = this.getAttribLocation("normal");
        glVertexAttribPointer(normalAttrib, 3, GL_FLOAT, false, 32, 12);
        glEnableVertexAttribArray(normalAttrib);
        int texCoordAttrib = this.getAttribLocation("texCoord");
        glVertexAttribPointer(texCoordAttrib, 2, GL_FLOAT, false, 32, 24);
        glEnableVertexAttribArray(texCoordAttrib);
    }

    abstract public void linkAttributes();
    abstract protected void linkUniforms(Camera camera, Entity entity);

    public void use() {
        glUseProgram(this.program);
        glBindVertexArray(this.vao);
    }

    protected int getAttribLocation(String attrib) {
        return glGetAttribLocation(this.program, attrib);
    }

    protected int getUniformLocation(String uniform) {
        return glGetUniformLocation(this.program, uniform);
    }

    public void bind(Camera camera, Entity entity) {
        this.use();
        this.linkUniforms(camera, entity);
    }
}
