package com.glowstick.engine.game;

import com.glowstick.engine.graphics.Model;
import com.glowstick.engine.caches.ModelCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MainMenu {
    @Autowired
    private ModelCache modelCache;

    private Model model;

    @PostConstruct
    private void init() {
        try {
            this.model = this.modelCache.get("dummy");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw() {
        this.model.draw();
    }
}
