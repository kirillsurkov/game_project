package com.glowstick.engine.geometry;

import lombok.Getter;
import org.lwjgl.util.vector.Vector3f;

@Getter
public class Vertex {
    private Vector3f coodrs;

    public Vertex(Vector3f coords) {
        this.coodrs = coords;
    }
}
