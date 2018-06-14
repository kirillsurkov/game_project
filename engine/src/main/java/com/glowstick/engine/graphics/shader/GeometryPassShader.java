package com.glowstick.engine.graphics.shader;

import com.glowstick.engine.game.camera.Camera;
import com.glowstick.engine.game.Entity;
import com.glowstick.engine.graphics.Shader;
import com.glowstick.engine.graphics.Texture;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.GL_TEXTURE1;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;

public class GeometryPassShader extends Shader {
    public GeometryPassShader(String name, int program) {
        super(name, program);
    }

    @Override
    protected void linkUniforms(Camera camera, Entity entity) {
        Matrix4f model = entity.getModelMatrix();
        Matrix4f view = camera.getViewMatrix();
        Matrix4f projection = camera.getProjectionMatrix();
        Matrix4f modelView = Matrix4f.mul(view, model, new Matrix4f());
        Matrix4f normal = Matrix4f.invert(modelView, new Matrix4f());
        Matrix4f.transpose(normal, normal);
        Matrix4f MVP = Matrix4f.mul(projection, modelView, new Matrix4f());

        FloatBuffer modelViewFloatBuffer = FloatBuffer.allocate(16);
        modelView.store(modelViewFloatBuffer);
        glUniformMatrix4fv(this.getUniformLocation("uModelView"), false, modelViewFloatBuffer.array());

        FloatBuffer normalFloatBuffer = FloatBuffer.allocate(16);
        normal.store(normalFloatBuffer);
        glUniformMatrix4fv(this.getUniformLocation("uNormal"), false, normalFloatBuffer.array());

        FloatBuffer MVPFloatBuffer = FloatBuffer.allocate(16);
        MVP.store(MVPFloatBuffer);
        glUniformMatrix4fv(this.getUniformLocation("uMVP"), false, MVPFloatBuffer.array());

        glActiveTexture(GL_TEXTURE0);
        entity.getDiffuseMap().bind();
        glUniform1i(this.getUniformLocation("uDiffuse"), 0);

        glActiveTexture(GL_TEXTURE1);
        entity.getGlowMap().bind();
        glUniform1i(this.getUniformLocation("uGlow"), 1);
    }
}
