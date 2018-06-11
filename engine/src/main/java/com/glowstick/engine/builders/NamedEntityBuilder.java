package com.glowstick.engine.builders;

import com.glowstick.engine.extension.Named;
import com.glowstick.engine.cache.ModelCache;
import com.glowstick.engine.cache.ShaderCache;
import com.glowstick.engine.game.Entity;

public abstract class NamedEntityBuilder<T extends Entity> implements Named {
    abstract public T build(ModelCache modelCache, ShaderCache shaderCache) throws Exception;
}
