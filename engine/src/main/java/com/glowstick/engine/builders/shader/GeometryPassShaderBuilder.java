package com.glowstick.engine.builders.shader;

import com.glowstick.engine.builders.NamedShaderBuilder;
import com.glowstick.engine.graphics.shader.GeometryPassShader;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class GeometryPassShaderBuilder extends NamedShaderBuilder<GeometryPassShader> {
    private final String name = "geometrypass";

    @Override
    protected GeometryPassShader build(int program, int vao) {
        return new GeometryPassShader(this.name, program, vao);
    }
}
