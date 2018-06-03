package com.glowstick.engine.game;

import com.glowstick.engine.builders.EntityBuilder;
import com.glowstick.engine.service.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    private EntityBuilder entityBuilder;
    private List<Entity> entities;

    protected Scene(EntityBuilder entityBuilder) {
        this.entityBuilder = entityBuilder;
        this.entities = new ArrayList<>();
    }

    protected void addEntity(String name) throws Exception {
        this.entities.add(this.entityBuilder.build(name));
    }

    public void draw(double delta) {
        this.entities.forEach(entity -> {
            entity.update(delta);
            entity.draw();
        });
    }
}
