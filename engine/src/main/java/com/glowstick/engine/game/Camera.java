package com.glowstick.engine.game;

import com.glowstick.engine.Utils;
import lombok.Getter;
import org.lwjgl.util.vector.Matrix4f;

public class Camera {
    @Getter
    private Matrix4f projectionMatrix;
    @Getter
    private Matrix4f viewMatrix = new Matrix4f();
    private Matrix4f matrix2D = new Matrix4f();
    private Matrix4f matrix3D = new Matrix4f();

    public Camera() {
        Utils.ortho2D(this.matrix2D, -1, 1, -1, 1, -1, 1);
        this.set2D();
    }

    public void set2D() {
        this.projectionMatrix = this.matrix2D;
    }

    public void set3D() {
        this.projectionMatrix = this.matrix3D;
    }
}
