package com.glowstick.engine.builders;

public interface NamedBuilder<T> {
    String getName();
    T build() throws Exception;
}
