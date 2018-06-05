package com.glowstick.engine.game;

import com.glowstick.engine.builders.EntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MainMenu {
    @Autowired
    private EntityBuilder entityBuilder;

    private Entity dummyEntity;

    @PostConstruct
    private void init() throws Exception {
        this.dummyEntity = this.entityBuilder.build("dummy");
    }

    public void draw() {
        this.dummyEntity.draw(null);
    }
}
