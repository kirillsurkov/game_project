package com.glowstick.engine.game.camera;

import com.glowstick.engine.Utils;
import lombok.Getter;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.springframework.stereotype.Component;

@Component
public class Camera2D implements Camera {
    @Getter
    private Matrix4f projectionMatrix = new Matrix4f();
    @Getter
    private Matrix4f viewMatrix = new Matrix4f();
    @Getter
    private Vector3f position = new Vector3f();

    public Camera2D() {
        Utils.ortho2D(this.projectionMatrix, -5, 5, -5, 5, -10, 10);
        Utils.lookAt(this.viewMatrix, this.position, new Vector3f(0, 0, -1), new Vector3f(0, 1, 0));
    }
}
