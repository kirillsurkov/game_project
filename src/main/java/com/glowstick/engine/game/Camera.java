package com.glowstick.engine.game;

import com.glowstick.engine.service.Utils;
import lombok.Getter;
import org.lwjgl.util.vector.Matrix4f;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Camera {
    private Matrix4f projectionMatrix = new Matrix4f();
    private Matrix4f viewMatrix = new Matrix4f();

    public Camera() {
        Utils.ortho2D(this.projectionMatrix, -1, 1, -1, 1, -1, 1);
    }
}
