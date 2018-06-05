package com.glowstick.game.core;

import com.glowstick.engine.game.Scene;
import com.glowstick.engine.builders.EntityBuilder;
import com.glowstick.engine.graphics.Graphics;
import com.glowstick.engine.graphics.Window;
import com.glowstick.engine.game.InputListener;
import com.glowstick.game.scenes.Scene01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Q;

@Component
public class Game {
    @Autowired
    private Window window;
    @Autowired
    private Graphics graphics;
    @Autowired
    private InputListener inputListener;
    @Autowired
    private EntityBuilder entityBuilder;
//    @Autowired
    private MainMenu mainMenu;
    private Scene scene;
    private boolean paused = true;

    @PostConstruct
    private void init() throws Exception {
        this.scene = new Scene01(this.entityBuilder);
        this.graphics.setOnDraw(this::update);
        this.graphics.loop();
    }

    private void update(double delta) {
        if (this.inputListener.isKeyDown(GLFW_KEY_Q)) window.close();
        if (this.inputListener.isKeyPressed(GLFW_KEY_ESCAPE)) this.paused = !this.paused;
        if (this.paused) {
//            this.mainMenu.draw();
        } else {
            this.scene.draw(delta);
        }
    }
}