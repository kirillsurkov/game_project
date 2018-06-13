package com.glowstick.engine.builders;

import com.glowstick.engine.cache.ModelCache;
import com.glowstick.engine.cache.ShaderCache;
import com.glowstick.engine.cache.TextureCache;
import com.glowstick.engine.game.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EntityBuilder implements Builder<Entity> {
    @Autowired
    private ModelCache modelCache;

    @Autowired
    private ShaderCache shaderCache;

    @Autowired
    private TextureCache textureCache;

    @Autowired
    private List<? extends NamedEntityBuilder> builders;

    private Map<String, NamedEntityBuilder> buildersByName = new HashMap<>();

    @PostConstruct
    private void init() {
        this.builders.forEach(builder ->
                this.buildersByName.put(builder.getName(), builder)
        );
    }

    @Override
    public Entity build(String name) throws Exception {
        if (this.buildersByName.containsKey(name)) {
            return this.buildersByName.get(name).build(this.modelCache, this.shaderCache, this.textureCache);
        } else {
            throw new Exception("Entity '" + name + "' not found");
        }
    }
}
