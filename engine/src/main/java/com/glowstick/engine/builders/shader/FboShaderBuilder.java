package com.glowstick.engine.builders.shader;

import com.glowstick.engine.builders.NamedShaderBuilder;
import com.glowstick.engine.graphics.shader.FboShader;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class FboShaderBuilder extends NamedShaderBuilder<FboShader> {
    private final String name = "fbo";

    @Override
    protected FboShader build(int program, int vao) {
        return new FboShader(this.name, program, vao);
    }
}
