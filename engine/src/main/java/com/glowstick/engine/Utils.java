package com.glowstick.engine;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

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

    public static void lookAt(Matrix4f matrix, Vector3f eye, Vector3f target, Vector3f upDir) {
        Vector3f forward = new Vector3f();
        Vector3f.sub(eye, target, forward).normalise(forward);
        Vector3f left = new Vector3f();
        Vector3f.cross(upDir, forward, left).normalise(left);
        Vector3f up = new Vector3f();
        Vector3f.cross(forward, left, up);
        matrix.setIdentity();
        matrix.m00 = left.x;
        matrix.m10 = left.y;
        matrix.m20 = left.z;
        matrix.m30 = -left.x * eye.x - left.y * eye.y - left.z * eye.z;
        matrix.m01 = up.x;
        matrix.m11 = up.y;
        matrix.m21 = up.z;
        matrix.m31 = -up.x * eye.x - up.y * eye.y - up.z * eye.z;
        matrix.m02 = forward.x;
        matrix.m12 = forward.y;
        matrix.m22 = forward.z;
        matrix.m32 = -forward.x * eye.x - forward.y * eye.y - forward.z * eye.z;
    }

    public static void perspective(Matrix4f matrix, float fovy, float aspect, float zNear, float zFar) {
        float f = (float)(1.0f / Math.tan(fovy / 2.0));
        matrix.setIdentity();
        matrix.m00 = f / aspect;
        matrix.m11 = -f;
        matrix.m22 = (zFar + zNear) / (zNear - zFar);
        matrix.m23 = -1;
        matrix.m32 = 2 * zFar * zNear / (zNear - zFar);
        matrix.m33 = 0;
    }
}
