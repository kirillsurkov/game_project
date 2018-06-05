package com.glowstick.engine.builders;

import com.glowstick.engine.builders.entity.NamedEntityBuilder;
import com.glowstick.engine.caches.ModelCache;
import com.glowstick.engine.caches.ShaderCache;
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
    protected ModelCache modelCache;

    @Autowired
    protected ShaderCache shaderCache;

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
            return this.buildersByName.get(name).build(this.modelCache, this.shaderCache);
        } else {
            throw new Exception("Entity '" + name + "' not found");
        }
    }
}
