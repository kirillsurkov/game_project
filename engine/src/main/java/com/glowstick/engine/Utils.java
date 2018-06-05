package com.glowstick.engine;

import org.lwjgl.util.vector.Matrix4f;

public class Utils {
    public static void ortho2D(Matrix4f matrix, float left, float right, float bottom, float top, float near, float far) {
        matrix.setIdentity();
        matrix.m00 = 2.0f / (right - left);
        matrix.m11 = 2.0f / (top - bottom);
        matrix.m22 = 2.0f / (near - far);
        matrix.m30 = (right + left) / (left - right);
        matrix.m31 = (top + bottom) / (bottom - top);
        matrix.m32 = (far + near) / (near - far);
    }
}
