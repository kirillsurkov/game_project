package com.glowstick.engine.game.entities;

import com.glowstick.engine.caches.ModelCache;
import com.glowstick.engine.graphics.Model;
import com.glowstick.engine.service.Entity;

public class CubeEntity extends Entity {
    private Model model;

    public CubeEntity(ModelCache modelCache) throws Exception {
        this.model = modelCache.get("triangle");
    }

    @Override
    public void update(double delta) {
    }

    @Override
    public void draw() {
        this.model.draw();
    }
}
