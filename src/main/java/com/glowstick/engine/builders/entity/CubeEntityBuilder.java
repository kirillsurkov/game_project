package com.glowstick.engine.builders.entity;

import com.glowstick.engine.game.entities.CubeEntity;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CubeEntityBuilder extends NamedEntityBuilder<CubeEntity> {
    private final String name = "cube";

    @Override
    public CubeEntity build() throws Exception {
        return new CubeEntity(this.modelCache, this.shaderCache);
    }
}
