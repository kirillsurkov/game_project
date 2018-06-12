package com.glowstick.engine.game.entity;

import com.glowstick.engine.cache.ModelCache;
import com.glowstick.engine.cache.ShaderCache;
import com.glowstick.engine.game.Entity;
import lombok.Getter;

public class FboEntity extends Entity {
    @Getter
    private final int frameBuffer;
    @Getter
    private final int depthTexture;
    @Getter
    private final int positionTexture;
    @Getter
    private final int normalTexture;
    @Getter
    private final int colorTexture;

    public FboEntity(ModelCache modelCache, ShaderCache shaderCache, int frameBuffer, int depthTexture, int positionTexture, int normalTexture, int colorTexture) throws Exception {
        super(modelCache, shaderCache);
        this.addModel("plane", "fbo");
        this.frameBuffer = frameBuffer;
        this.depthTexture = depthTexture;
        this.positionTexture = positionTexture;
        this.normalTexture = normalTexture;
        this.colorTexture = colorTexture;
    }

    @Override
    public void update(double delta) {
    }
}
