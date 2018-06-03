package com.glowstick.engine.builders.shaderbuilders;

import com.glowstick.engine.graphics.shaders.AlternativeShader;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AlternativeShaderBuilder extends AbstractShaderBuilder<AlternativeShader> {
    private String name = "alternative";

    @Override
    protected AlternativeShader build(int program) {
        return new AlternativeShader(this.name, program);
    }
}
