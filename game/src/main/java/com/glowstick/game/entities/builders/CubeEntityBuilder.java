package com.glowstick.game.entities.builders;

import com.glowstick.engine.builders.NamedEntityBuilder;
import com.glowstick.engine.cache.ModelCache;
import com.glowstick.engine.cache.ShaderCache;
import com.glowstick.engine.cache.TextureCache;
import com.glowstick.game.entities.CubeEntity;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CubeEntityBuilder extends NamedEntityBuilder<CubeEntity> {
    private final String name = "cube";

    @Override
    public CubeEntity build(ModelCache modelCache, ShaderCache shaderCache, TextureCache textureCache) throws Exception {
        return new CubeEntity(modelCache, shaderCache, textureCache);
    }
}
