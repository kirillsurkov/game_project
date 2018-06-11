package com.glowstick.engine.builders.entity;

import com.glowstick.engine.builders.NamedEntityBuilder;
import com.glowstick.engine.cache.ModelCache;
import com.glowstick.engine.cache.ShaderCache;
import com.glowstick.engine.game.entity.FboEntity;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class FboEntityBuilder extends NamedEntityBuilder<FboEntity> {
    private String name = "fbo";

    @Override
    public FboEntity build(ModelCache modelCache, ShaderCache shaderCache) throws Exception {
        return new FboEntity(modelCache, shaderCache);
    }
}
