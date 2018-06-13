package com.glowstick.game.scenes;

import com.glowstick.engine.builders.EntityBuilder;
import com.glowstick.engine.game.Scene;
import com.glowstick.engine.game.camera.Camera;

public class Scene01 extends Scene {
    public Scene01(EntityBuilder entityBuilder, Camera camera) throws Exception {
        super(entityBuilder, camera);
        this.addEntity("cube").color(1, 0, 0).scale(0.5f).move(-5, 0, 5);
        this.addEntity("cube").color(0, 0, 1).scale(0.5f).move( 0, 0, 5);
        this.addEntity("cube").color(1, 1, 1).scale(0.5f).move( 5, 0, 5);
    }
}
