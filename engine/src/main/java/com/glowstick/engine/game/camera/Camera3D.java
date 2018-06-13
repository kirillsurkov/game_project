package com.glowstick.engine.game.camera;

import com.glowstick.engine.Utils;
import lombok.Getter;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.springframework.stereotype.Component;

@Component
public class Camera3D implements Camera {
    @Getter
    private Matrix4f projectionMatrix = new Matrix4f();
    @Getter
    private Matrix4f viewMatrix = new Matrix4f();
    @Getter
    private Vector3f position = new Vector3f(0, 0, 0);
    private Vector3f target = new Vector3f(0, 0, 1);
    private Vector3f upDir = new Vector3f(0, 1, 0);

    public Camera3D() {
        Utils.perspective(this.projectionMatrix, (float)(Math.PI/2.0), 800.0f/600.0f, 0.01f, 1000);
        Utils.lookAt(this.viewMatrix, this.position, this.target, this.upDir);
    }
}
