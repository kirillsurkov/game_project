package com.glowstick.engine.game;

import com.glowstick.engine.service.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    private List<Entity> entities;

    protected Scene() {
        this.entities = new ArrayList<>();
    }

    protected void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public void draw(double delta) {
        this.entities.forEach(entity -> {
            entity.update(delta);
            entity.draw();
        });
    }
}
