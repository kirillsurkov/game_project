package com.glowstick.engine.graphics.shader;

import com.glowstick.engine.graphics.Shader;
import com.glowstick.engine.game.Camera;
import com.glowstick.engine.game.Entity;

import static org.lwjgl.opengl.GL20.glUniform1i;

public class FboShader extends Shader {
    public FboShader(String name, int program, int vao) {
        super(name, program, vao);
    }

    @Override
    public void linkAttributes() {
        this.linkVertexAttributes();
    }

    @Override
    protected void linkUniforms(Camera camera, Entity entity) {
        glUniform1i(this.getUniformLocation("colorTexture"), 0);
        glUniform1i(this.getUniformLocation("positionTexture"), 1);
        glUniform1i(this.getUniformLocation("normalTexture"), 2);
        glUniform1i(this.getUniformLocation("depthTexture"), 3);
    }
}
