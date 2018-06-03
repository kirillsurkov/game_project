package com.glowstick.engine.builders;

import com.glowstick.engine.graphics.Texture;
import org.springframework.stereotype.Component;

@Component
public class TextureBuilder implements Builder<Texture> {
    @Override
    public Texture build(String name) throws Exception {
        throw new Exception("Method not implemented");
    }
}
