package com.glowstick.engine.game.scenes;

import com.glowstick.engine.game.Scene;
import com.glowstick.engine.game.entities.Cube;

public class Scene01 extends Scene {
    public Scene01() {
        this.addEntity(new Cube());
        this.addEntity(new Cube());
    }
}
