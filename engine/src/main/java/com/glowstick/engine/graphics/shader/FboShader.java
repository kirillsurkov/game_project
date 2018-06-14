package com.glowstick.engine.graphics.shader;

import com.glowstick.engine.graphics.Shader;
import com.glowstick.engine.game.camera.Camera;
import com.glowstick.engine.game.Entity;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUniform3f;

public class FboShader extends Shader {
    public FboShader(String name, int program) {
        super(name, program);
    }

    @Override
    protected void linkUniforms(Camera camera, Entity entity) {
        glUniform1i(this.getUniformLocation("colorTexture"), 0);
        glUniform1i(this.getUniformLocation("positionTexture"), 1);
        glUniform1i(this.getUniformLocation("normalTexture"), 2);
        glUniform1i(this.getUniformLocation("glowTexture"), 3);
        glUniform1i(this.getUniformLocation("depthTexture"), 4);

        Vector3f cameraPos = camera.getPosition();
        glUniform3f(this.getUniformLocation("cameraPos"), cameraPos.x, cameraPos.y, cameraPos.z);
    }
}
