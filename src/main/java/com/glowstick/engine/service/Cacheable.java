package com.glowstick.engine.service;

import lombok.Getter;

@Getter
public abstract class Cacheable {
    private String name;

    public Cacheable(String name) {
        this.name = name;
    }
}
