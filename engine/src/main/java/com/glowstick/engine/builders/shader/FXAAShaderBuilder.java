package com.glowstick.engine.builders.shader;

import com.glowstick.engine.builders.NamedShaderBuilder;
import com.glowstick.engine.graphics.shader.FXAAShader;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class FXAAShaderBuilder extends NamedShaderBuilder<FXAAShader> {
    private final String name = "fxaa";

    @Override
    protected FXAAShader build(int program) {
        return new FXAAShader(this.name, program);
    }
}
