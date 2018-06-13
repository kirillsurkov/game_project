package com.glowstick.engine.game.camera;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public interface Camera {
    Matrix4f getProjectionMatrix();
    Matrix4f getViewMatrix();
    Vector3f getPosition();
}
