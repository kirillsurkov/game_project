package com.glowstick.engine.graphics.shaders;

import com.glowstick.engine.game.Camera;
import com.glowstick.engine.graphics.Shader;
import com.glowstick.engine.service.Entity;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

public class DefaultShader extends Shader {
    public DefaultShader(String name, int program) {
        super(name, program);
    }

    @Override
    public void linkAttributes() {
        int posAttrib = this.getAttribLocation("position");
        glVertexAttribPointer(posAttrib, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(posAttrib);
    }

    @Override
    public void linkUniforms(Camera camera, Entity entity) {
    }
}
