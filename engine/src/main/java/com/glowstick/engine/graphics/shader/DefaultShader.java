package com.glowstick.engine.graphics.shader;

import com.glowstick.engine.game.camera.Camera;
import com.glowstick.engine.graphics.Shader;
import com.glowstick.engine.game.Entity;

public class DefaultShader extends Shader {
    public DefaultShader(String name, int program) {
        super(name, program);
    }

    @Override
    protected void linkUniforms(Camera camera, Entity entity) {
    }
}
