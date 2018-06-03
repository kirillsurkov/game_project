package com.glowstick.engine.service;

import com.glowstick.engine.geometry.Point;

public abstract class Entity {
    private Point coords;

    abstract public void update(double delta);
    abstract public void draw();
}
