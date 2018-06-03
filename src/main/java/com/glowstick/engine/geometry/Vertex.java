package com.glowstick.engine.geometry;

import lombok.Getter;

@Getter
public class Vertex {
    private Point coodrs;

    public Vertex(Point coords) {
        this.coodrs = coords;
    }
}
