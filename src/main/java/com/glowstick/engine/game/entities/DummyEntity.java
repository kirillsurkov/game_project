package com.glowstick.engine.game.entities;

import com.glowstick.engine.caches.ModelCache;
import com.glowstick.engine.caches.ShaderCache;
import com.glowstick.engine.game.Entity;

public class DummyEntity extends Entity {
    public DummyEntity(ModelCache modelCache, ShaderCache shaderCache) throws Exception {
        super(modelCache, shaderCache);
        this.addModel("dummy", "default");
    }

    @Override
    public void update(double delta) {
    }
}
