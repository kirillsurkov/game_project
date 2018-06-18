package com.glowstick.game.scenes;

import com.glowstick.engine.builders.EntityBuilder;
import com.glowstick.engine.game.Scene;
import com.glowstick.engine.game.camera.Camera;
import org.lwjgl.util.vector.Vector3f;

public class Scene01 extends Scene {
    public Scene01(EntityBuilder entityBuilder, Camera camera) throws Exception {
        super(entityBuilder, camera);
        this.addEntity("cube").move(0, 0, -2.5f).rotate((float)(Math.PI / 6), new Vector3f(0, 1, 0));
    }
}
