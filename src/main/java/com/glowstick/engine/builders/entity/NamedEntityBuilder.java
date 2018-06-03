package com.glowstick.engine.builders.entity;

import com.glowstick.engine.builders.NamedBuilder;
import com.glowstick.engine.caches.ModelCache;
import com.glowstick.engine.caches.ShaderCache;
import com.glowstick.engine.service.Entity;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class NamedEntityBuilder<T extends Entity> implements NamedBuilder<T> {
    @Autowired
    protected ModelCache modelCache;

    @Autowired
    protected ShaderCache shaderCache;

    abstract public T build() throws Exception;
}
