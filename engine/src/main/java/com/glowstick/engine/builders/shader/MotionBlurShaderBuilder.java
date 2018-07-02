package com.glowstick.engine.builders.shader;

import com.glowstick.engine.builders.NamedShaderBuilder;
import com.glowstick.engine.graphics.shader.MotionBlurShader;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MotionBlurShaderBuilder extends NamedShaderBuilder<MotionBlurShader> {
    private final String name = "motionblur";

    @Override
    protected MotionBlurShader build(int program) {
        return new MotionBlurShader(this.name, program);
    }
}
