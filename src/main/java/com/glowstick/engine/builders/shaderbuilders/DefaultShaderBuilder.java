package com.glowstick.engine.builders.shaderbuilders;

import com.glowstick.engine.graphics.shaders.DefaultShader;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class DefaultShaderBuilder extends AbstractShaderBuilder<DefaultShader> {
    private String name = "default";

    @Override
    protected DefaultShader build(int program) {
        return new DefaultShader(this.name, program);
    }
}
