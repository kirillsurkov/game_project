package com.glowstick.engine.graphics.shader;

import com.glowstick.engine.game.Entity;
import com.glowstick.engine.game.camera.Camera;
import com.glowstick.engine.graphics.Shader;

import static org.lwjgl.opengl.GL20.glUniform1i;

public class BlurShader extends Shader {
    public BlurShader(String name, int program) {
        super(name, program);
    }

    @Override
    protected void linkUniforms(Camera camera, Entity entity) {
        glUniform1i(this.getUniformLocation("colorTexture"), 0);
        glUniform1i(this.getUniformLocation("glowTexture"), 1);
    }
}
