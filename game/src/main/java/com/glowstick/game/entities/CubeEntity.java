package com.glowstick.game.entities;

import com.glowstick.engine.cache.ModelCache;
import com.glowstick.engine.cache.ShaderCache;
import com.glowstick.engine.cache.TextureCache;
import com.glowstick.engine.game.Entity;
import org.lwjgl.util.vector.Vector3f;

public class CubeEntity extends Entity {
    private double time = 0;

    public CubeEntity(ModelCache modelCache, ShaderCache shaderCache, TextureCache textureCache) throws Exception {
        super(modelCache, shaderCache, textureCache);
        this.addModel("dummy", "geometrypass");
    }

    @Override
    public void update(double delta) {
        this.time += delta;
        this.rotate((float)delta, new Vector3f(0, 1, 0));
//        this.move((float)Math.cos(this.time * 10) / 10.0f, (float)Math.sin(this.time * 10) / 10.0f);
    }
}
