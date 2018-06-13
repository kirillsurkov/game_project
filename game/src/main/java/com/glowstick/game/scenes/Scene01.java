package com.glowstick.game.scenes;

import com.glowstick.engine.builders.EntityBuilder;
import com.glowstick.engine.game.Scene;
import com.glowstick.engine.game.camera.Camera;

public class Scene01 extends Scene {
    public Scene01(EntityBuilder entityBuilder, Camera camera) throws Exception {
        super(entityBuilder, camera);
        this.addEntity("cube").move(0, 0, -2.5f);
    }
}
