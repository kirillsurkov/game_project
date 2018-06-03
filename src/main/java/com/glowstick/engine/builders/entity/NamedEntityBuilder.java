package com.glowstick.engine.builders.entity;

import com.glowstick.engine.builders.NamedBuilder;
import com.glowstick.engine.service.Entity;

public abstract class NamedEntityBuilder<T extends Entity> implements NamedBuilder<T> {
    abstract public T build() throws Exception;
}
