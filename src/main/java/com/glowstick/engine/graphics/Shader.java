package com.glowstick.engine.graphics;

import com.glowstick.engine.service.Cacheable;

import static org.lwjgl.opengl.GL20.*;

public abstract class Shader extends Cacheable {
    private final int program;

    public Shader(String name, int program) {
        super(name);
        this.program = program;
    }

    abstract public void link();

    protected int getAttribLocation(String attrib) {
        return glGetAttribLocation(this.program, attrib);
    }

    public void use() {
        glUseProgram(this.program);
    }
}
