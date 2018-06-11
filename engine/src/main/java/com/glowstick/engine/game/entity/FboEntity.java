package com.glowstick.engine.game.entity;

import com.glowstick.engine.cache.ModelCache;
import com.glowstick.engine.cache.ShaderCache;
import com.glowstick.engine.game.Entity;

public class FboEntity extends Entity {
    public FboEntity(ModelCache modelCache, ShaderCache shaderCache) throws Exception {
        super(modelCache, shaderCache);
        this.addModel("plane", "fbo");
    }

    @Override
    public void update(double delta) {
    }
}
