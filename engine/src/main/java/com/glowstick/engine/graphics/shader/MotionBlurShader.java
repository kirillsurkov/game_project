package com.glowstick.engine.graphics.shader;

import com.glowstick.engine.game.Entity;
import com.glowstick.engine.game.camera.Camera;
import com.glowstick.engine.graphics.Shader;
import lombok.Getter;

import static org.lwjgl.opengl.GL20.glUniform1fv;
import static org.lwjgl.opengl.GL20.glUniform1iv;

public class MotionBlurShader extends Shader {
    @Getter
    private final int framesCount = 4;
    private int[] frames;
    private float[] kernel;

    public MotionBlurShader(String name, int program) {
        super(name, program);
        this.kernel = new float[this.framesCount];
        this.frames = new int[this.framesCount];
        for (int i = 0; i < this.framesCount; i++) {
            this.frames[i] = i;
        }
    }

    @Override
    protected void linkUniforms(Camera camera, Entity entity) {
        glUniform1iv(this.getUniformLocation("colorTexture"), this.frames);
        glUniform1fv(this.getUniformLocation("kernel"), this.kernel);
    }

    public void setBlur(float sigma) {
        double sum = 0;
        for (int i = 0; i < this.frames.length; i++) {
            this.kernel[i] = (float)(Math.exp(-(i*i)/(2*sigma*sigma)) / (Math.sqrt(Math.PI*2) * sigma));
            sum += this.kernel[i];
        }
        for (int i = 0; i < this.frames.length; i++) {
            this.kernel[i] /= sum;
        }
    }
}
