package com.glowstick.engine.game.entity;

import com.glowstick.engine.cache.ModelCache;
import com.glowstick.engine.cache.ShaderCache;
import com.glowstick.engine.cache.TextureCache;
import com.glowstick.engine.game.Entity;
import com.glowstick.engine.graphics.Texture;
import lombok.Getter;

@Getter
public class FboEntity extends Entity {
    private final int frameBuffer;
    private final Texture depthTexture;
    private final Texture glowTexture;
    private final Texture positionTexture;
    private final Texture normalTexture;
    private final Texture colorTexture;

    public FboEntity(ModelCache modelCache, ShaderCache shaderCache, TextureCache textureCache, int frameBuffer, Texture depthTexture, Texture colorTexture, Texture positionTexture, Texture normalTexture, Texture glowTexture) throws Exception {
        super(modelCache, shaderCache, textureCache);
        this.addModel("plane", "fbo");
        this.frameBuffer = frameBuffer;
        this.depthTexture = depthTexture;
        this.colorTexture = colorTexture;
        this.positionTexture = positionTexture;
        this.normalTexture = normalTexture;
        this.glowTexture = glowTexture;
    }

    @Override
    public void update(double delta) {
    }
}
