package com.glowstick.engine.builders;

import com.glowstick.engine.builders.shaderbuilders.AbstractShaderBuilder;
import com.glowstick.engine.graphics.Shader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ShaderBuilder implements Builder<Shader> {
    @Autowired
    private List<? extends AbstractShaderBuilder> builders;

    private Map<String, AbstractShaderBuilder> buildersByName = new HashMap<>();

    @PostConstruct
    private void init() {
        this.builders.forEach(builder ->
            this.buildersByName.put(builder.getName(), builder)
        );
    }

    @Override
    public Shader build(String name) throws Exception {
        if (this.buildersByName.containsKey(name)) {
            return this.buildersByName.get(name).build();
        } else {
            throw new Exception("Shader '" + name + "' not found");
        }
    }
}
