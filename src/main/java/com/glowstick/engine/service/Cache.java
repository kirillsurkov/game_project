package com.glowstick.engine.service;

import com.glowstick.engine.builders.Builder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public abstract class Cache<T extends Cacheable> {
    @Autowired
    protected Builder<T> builder;

    private Map<String, T> objects = new HashMap<>();

    protected T load(String name) throws Exception {
        return this.builder.build(name);
    }

    public T get(String name) throws Exception {
        if (this.objects.get(name) == null) this.objects.put(name, this.load(name));
        return this.objects.get(name);
    }
}
