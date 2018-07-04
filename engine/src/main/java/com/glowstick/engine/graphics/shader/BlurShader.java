package com.glowstick.engine.graphics.shader;

import com.glowstick.engine.game.Entity;
import com.glowstick.engine.game.camera.Camera;
import com.glowstick.engine.graphics.Shader;
import lombok.Setter;

import static org.lwjgl.opengl.GL20.glUniform1fv;
import static org.lwjgl.opengl.GL20.glUniform1i;

public class BlurShader extends Shader {
    @Setter
    private boolean firstPass;
    private final int kernelSize = 13;
    private float[] kernel;

    public BlurShader(String name, int program) {
        super(name, program);
        this.kernel = new float[this.kernelSize];
    }

    @Override
    protected void linkUniforms(Camera camera, Entity entity) {
        glUniform1i(this.getUniformLocation("colorTexture"), 0);
        glUniform1i(this.getUniformLocation("firstPass"), this.firstPass ? 1 : 0);
        glUniform1fv(this.getUniformLocation("kernel"), this.kernel);
    }

    public void setBlur(float sigma) {
        double sum = 0;
        for (int i = 0; i < this.kernel.length; i++) {
            this.kernel[i] = (float)(Math.exp(-(i*i)/(2*sigma*sigma)) / (Math.sqrt(Math.PI*2) * sigma));
            sum += this.kernel[i];
        }
/*        for (int i = 0; i < this.kernelSize; i++) {
            this.kernel[i] /= sum;
        }*/
    }
}
