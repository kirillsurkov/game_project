package com.glowstick.engine.game;

import com.glowstick.engine.builders.EntityBuilder;
import com.glowstick.engine.game.camera.Camera;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    private EntityBuilder entityBuilder;
    private Camera camera;
    private List<Entity> entities;

    protected Scene(EntityBuilder entityBuilder, Camera camera) {
        this.entityBuilder = entityBuilder;
        this.camera = camera;
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
