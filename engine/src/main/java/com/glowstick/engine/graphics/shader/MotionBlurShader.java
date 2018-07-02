package com.glowstick.engine.graphics.shader;

import com.glowstick.engine.game.Entity;
import com.glowstick.engine.game.camera.Camera;
import com.glowstick.engine.graphics.Shader;

import static org.lwjgl.opengl.GL20.glUniform1i;

public class MotionBlurShader extends Shader {
    public MotionBlurShader(String name, int program) {
        super(name, program);
    }

    @Override
    protected void linkUniforms(Camera camera, Entity entity) {
        for (int i = 0; i < 30; i++) {
            glUniform1i(this.getUniformLocation("colorTexture" + i), i);
        }
    }
}
