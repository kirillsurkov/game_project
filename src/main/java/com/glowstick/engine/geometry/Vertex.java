package com.glowstick.engine.geometry;

import lombok.Getter;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

@Getter
public class Vertex {
    private Vector3f coodrs;
    private Vector3f normal;
    private Vector2f texCoords;

    public Vertex(Vector3f coords, Vector3f normal, Vector2f texCoords) {
        this.coodrs = coords;
        this.normal = normal;
        this.texCoords = texCoords;
    }
}
