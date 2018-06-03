package com.glowstick.engine.graphics.shaders;

import com.glowstick.engine.graphics.Shader;

import static org.lwjgl.opengl.GL11.GL_DOUBLE;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

public class DefaultShader extends Shader {
    public DefaultShader(String name, int program) {
        super(name, program);
    }

    @Override
    public void link() {
        int posAttrib = this.getAttribLocation("position");
        glVertexAttribPointer(posAttrib, 2, GL_DOUBLE, false, 0, 0);
        glEnableVertexAttribArray(posAttrib);
    }
}
