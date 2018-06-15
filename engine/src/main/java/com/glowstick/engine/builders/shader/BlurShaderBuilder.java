package com.glowstick.engine.builders.shader;

import com.glowstick.engine.builders.NamedShaderBuilder;
import com.glowstick.engine.graphics.shader.BlurShader;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class BlurShaderBuilder extends NamedShaderBuilder<BlurShader> {
    private final String name = "blur";

    @Override
    protected BlurShader build(int program) {
        return new BlurShader(this.name, program);
    }
}
