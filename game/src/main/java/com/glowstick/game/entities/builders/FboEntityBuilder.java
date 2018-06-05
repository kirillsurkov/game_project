package com.glowstick.game.entities.builders;

import com.glowstick.engine.builders.entity.NamedEntityBuilder;
import com.glowstick.engine.caches.ModelCache;
import com.glowstick.engine.caches.ShaderCache;
import com.glowstick.game.entities.FboEntity;
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
