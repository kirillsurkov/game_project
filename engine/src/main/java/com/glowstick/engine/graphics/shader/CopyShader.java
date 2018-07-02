package com.glowstick.engine.graphics.shader;

import com.glowstick.engine.game.Entity;
import com.glowstick.engine.game.camera.Camera;
import com.glowstick.engine.graphics.Shader;

public class CopyShader extends Shader {
    public CopyShader(String name, int program) {
        super(name, program);
    }

    @Override
    protected void linkUniforms(Camera camera, Entity entity) {
    }
}
