package com.glowstick.engine.game.entity;

import com.glowstick.engine.cache.ModelCache;
import com.glowstick.engine.cache.ShaderCache;
import com.glowstick.engine.cache.TextureCache;
import com.glowstick.engine.game.Entity;

public class DummyEntity extends Entity {
    public DummyEntity(ModelCache modelCache, ShaderCache shaderCache, TextureCache textureCache) throws Exception {
        super(modelCache, shaderCache, textureCache);
        this.addModel("dummy", "geometrypass");
    }

    @Override
    public void update(double delta) {
    }
}
