package com.glowstick.game.entities;

import com.glowstick.engine.caches.ModelCache;
import com.glowstick.engine.caches.ShaderCache;
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
