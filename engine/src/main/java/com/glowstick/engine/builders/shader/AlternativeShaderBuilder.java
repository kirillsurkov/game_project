package com.glowstick.engine.builders.shader;

import com.glowstick.engine.graphics.shaders.AlternativeShader;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AlternativeShaderBuilder extends NamedShaderBuilder<AlternativeShader> {
    private String name = "alternative";

    @Override
    protected AlternativeShader build(int program, int vao) {
        return new AlternativeShader(this.name, program, vao);
    }
}