package com.glowstick.engine.builders.shader;

import com.glowstick.engine.builders.NamedShaderBuilder;
import com.glowstick.engine.graphics.shader.CopyShader;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CopyShaderBuilder extends NamedShaderBuilder<CopyShader> {
    private String name = "copy";

    @Override
    protected CopyShader build(int program) {
        return new CopyShader(this.name, program);
    }
}
