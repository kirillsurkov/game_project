package com.glowstick.engine.graphics.shader;

import com.glowstick.engine.game.Camera;
import com.glowstick.engine.graphics.Shader;
import com.glowstick.engine.game.Entity;

import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

public class DefaultShader extends Shader {
    public DefaultShader(String name, int program, int vao) {
        super(name, program, vao);
    }

    @Override
    public void linkAttributes() {
        this.linkVertexAttributes();
    }

    @Override
    public void linkUniforms(Camera camera, Entity entity) {
    }
}