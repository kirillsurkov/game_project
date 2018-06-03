package com.glowstick.engine.builders;

public interface Builder<T> {
    T build(String name) throws Exception;
}
