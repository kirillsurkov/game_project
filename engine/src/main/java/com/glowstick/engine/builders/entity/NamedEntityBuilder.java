package com.glowstick.engine.builders.entity;

import com.glowstick.engine.extension.Named;
import com.glowstick.engine.caches.ModelCache;
import com.glowstick.engine.caches.ShaderCache;
import com.glowstick.engine.game.Entity;

public abstract class NamedEntityBuilder<T extends Entity> implements Named {
    abstract public T build(ModelCache modelCache, ShaderCache shaderCache) throws Exception;
}
