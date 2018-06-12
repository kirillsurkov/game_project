package com.glowstick.engine.graphics.shader;

import com.glowstick.engine.game.Camera;
import com.glowstick.engine.game.Entity;
import com.glowstick.engine.graphics.Shader;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.glUniform3f;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;

public class GeometryPassShader extends Shader {
    public GeometryPassShader(String name, int program, int vao) {
        super(name, program, vao);
    }

    @Override
    public void linkAttributes() {
        this.linkVertexAttributes();
    }

    @Override
    protected void linkUniforms(Camera camera, Entity entity) {
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

        Vector3f color = entity.getColor();
        glUniform3f(this.getUniformLocation("uColor"), color.x, color.y, color.z);
    }
}
