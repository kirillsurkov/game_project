package com.glowstick.engine.game.scenes;

import com.glowstick.engine.builders.EntityBuilder;
import com.glowstick.engine.game.Scene;

public class Scene01 extends Scene {
    public Scene01(EntityBuilder entityBuilder) throws Exception {
        super(entityBuilder);
        this.addEntity("cube");
        this.addEntity("cube");
    }
}
