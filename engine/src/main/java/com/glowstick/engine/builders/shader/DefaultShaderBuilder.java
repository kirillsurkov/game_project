package com.glowstick.engine.builders.shader;

import com.glowstick.engine.graphics.shaders.DefaultShader;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class DefaultShaderBuilder extends NamedShaderBuilder<DefaultShader> {
    private String name = "default";

    @Override
    protected DefaultShader build(int program, int vao) {
        return new DefaultShader(this.name, program, vao);
    }
}
