package com.glowstick.engine.game.entities;

import com.glowstick.engine.caches.ModelCache;
import com.glowstick.engine.caches.ShaderCache;
import com.glowstick.engine.service.Entity;

public class CubeEntity extends Entity {
    private double time = 0;

    public CubeEntity(ModelCache modelCache, ShaderCache shaderCache) throws Exception {
        super(modelCache, shaderCache);
        this.addModel("triangle", "alternative");
    }

    @Override
    public void update(double delta) {
        this.time += delta;
        this.move((float)Math.cos(this.time * 10) / 10.0f, (float)Math.sin(this.time * 10) / 10.0f);
    }
}
