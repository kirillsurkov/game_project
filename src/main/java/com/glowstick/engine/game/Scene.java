package com.glowstick.engine.game;

import com.glowstick.engine.builders.EntityBuilder;
import com.glowstick.engine.service.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    private Camera camera;
    private EntityBuilder entityBuilder;
    private List<Entity> entities;

    protected Scene(EntityBuilder entityBuilder) {
        this.camera = new Camera();
        this.entityBuilder = entityBuilder;
        this.entities = new ArrayList<>();
    }

    protected Entity addEntity(String name) throws Exception {
        Entity entity = this.entityBuilder.build(name);
        this.entities.add(entity);
        return entity;
    }

    public void draw(double delta) {
        this.entities.forEach(entity -> {
            entity.update(delta);
            entity.draw(this.camera);
        });
    }
}
