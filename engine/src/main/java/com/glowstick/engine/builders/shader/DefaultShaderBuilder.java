package com.glowstick.engine.builders.shader;

import com.glowstick.engine.builders.NamedShaderBuilder;
import com.glowstick.engine.graphics.shader.DefaultShader;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class DefaultShaderBuilder extends NamedShaderBuilder<DefaultShader> {
    private String name = "default";

    @Override
    protected DefaultShader build(int program) {
        return new DefaultShader(this.name, program);
    }
}
