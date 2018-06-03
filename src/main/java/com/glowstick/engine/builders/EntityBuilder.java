package com.glowstick.engine.builders;

import com.glowstick.engine.builders.entity.NamedEntityBuilder;
import com.glowstick.engine.service.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EntityBuilder implements Builder<Entity> {
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
            return this.buildersByName.get(name).build();
        } else {
            throw new Exception("Entity '" + name + "' not found");
        }
    }
}
