package com.glowstick.engine.game;

import com.glowstick.engine.builders.EntityBuilder;
import com.glowstick.engine.graphics.Graphics;
import com.glowstick.engine.graphics.Window;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class Game {
    @Autowired
    private Graphics graphics;
    @Autowired
    protected Window window;
    @Autowired
    protected EntityBuilder entityBuilder;
    @Autowired
    protected InputListener inputListener;

    abstract protected void init() throws Exception;
    abstract public void update(double delta);

    @PostConstruct
    private void innerInit() throws Exception {
        this.init();
        this.graphics.loop();
    }
}
