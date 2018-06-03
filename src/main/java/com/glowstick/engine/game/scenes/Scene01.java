package com.glowstick.engine.game.scenes;

import com.glowstick.engine.builders.EntityBuilder;
import com.glowstick.engine.game.Scene;

public class Scene01 extends Scene {
    public Scene01(EntityBuilder entityBuilder) throws Exception {
        super(entityBuilder);
        this.addEntity("cube").color(1, 0, 0).scale(0.5f).move(0.5f, -0.5f);
        this.addEntity("cube").color(0, 0, 1).scale(0.5f).move(0.25f, -0.25f);
        this.addEntity("cube").color(1, 1, 1).scale(0.5f);
    }
}
