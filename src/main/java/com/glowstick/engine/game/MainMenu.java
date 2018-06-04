package com.glowstick.engine.game;

import com.glowstick.engine.caches.ShaderCache;
import com.glowstick.engine.graphics.Model;
import com.glowstick.engine.caches.ModelCache;
import com.glowstick.engine.graphics.Shader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MainMenu {
    @Autowired
    private ShaderCache shaderCache;

    @Autowired
    private ModelCache modelCache;

    private Model model;
    private Shader shader;

    @PostConstruct
    private void init() throws Exception {
        this.model = this.modelCache.get("dummy");
        this.shader = this.shaderCache.get("default");
    }

    public void draw(Camera camera) {
        this.model.draw(this.shader, camera, null);
    }
}
