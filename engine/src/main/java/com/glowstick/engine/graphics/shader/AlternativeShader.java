package com.glowstick.engine.graphics.shader;

import com.glowstick.engine.game.camera.Camera;
import com.glowstick.engine.graphics.Shader;
import com.glowstick.engine.game.Entity;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;

public class AlternativeShader extends Shader {
    public AlternativeShader(String name, int program) {
        super(name, program);
    }

    @Override
    public void linkUniforms(Camera camera, Entity entity) {
        Matrix4f MVP = new Matrix4f();
        Matrix4f.mul(
                camera.getProjectionMatrix(),
                Matrix4f.mul(
                        camera.getViewMatrix(),
                        entity.getModelMatrix(),
                        MVP
                ),
                MVP
        );
        FloatBuffer floatBuffer = FloatBuffer.allocate(16);
        MVP.store(floatBuffer);
        glUniformMatrix4fv(this.getUniformLocation("uMVP"), false, floatBuffer.array());
    }
}
