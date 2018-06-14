package com.glowstick.game.core;

import com.glowstick.engine.game.Entity;
import com.glowstick.engine.builders.EntityBuilder;

public class MainMenu {
    private Entity dummyEntity;

    public MainMenu(EntityBuilder entityBuilder) throws Exception {
        this.dummyEntity = entityBuilder.build("dummy");
    }

    public void draw() {
//        this.dummyEntity.draw(null);
    }
}
