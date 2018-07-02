package com.glowstick.engine.game.camera;

import com.glowstick.engine.Utils;
import com.glowstick.engine.graphics.Window;
import lombok.Getter;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Camera3D implements Camera {
    @Getter
    private Matrix4f projectionMatrix = new Matrix4f();
    @Getter
    private Matrix4f viewMatrix = new Matrix4f();
    @Getter
    private Vector3f position = new Vector3f(0, 0, 0);
    private Vector3f target = new Vector3f(0, 0, -1);
    private Vector3f upDir = new Vector3f(0, 1, 0);

    @Autowired
    public Camera3D(Window window) {
        float ratio = (1.0f * window.getWidth()) / window.getHeight();
        Utils.perspective(this.projectionMatrix, (float)(Math.PI/2.0), ratio, 0.01f, 1000);
        Utils.lookAt(this.viewMatrix, this.position, this.target, this.upDir);
    }
}
