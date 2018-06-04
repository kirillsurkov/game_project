package com.glowstick.engine.game;

import com.glowstick.engine.builders.EntityBuilder;
import com.glowstick.engine.game.scenes.Scene01;
import com.glowstick.engine.graphics.Graphics;
import com.glowstick.engine.service.Window;
import com.glowstick.engine.service.InputListener;
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
    private Camera camera;
    @Autowired
    private EntityBuilder entityBuilder;
    @Autowired
    private MainMenu mainMenu;
    private Scene scene;
    private boolean paused = true;

    @PostConstruct
    private void init() throws Exception {
        this.scene = new Scene01(this.camera, this.entityBuilder);
        this.graphics.setOnDraw(this::update);
        this.graphics.loop();
    }

    private void update(double delta) {
        if (this.inputListener.isKeyDown(GLFW_KEY_Q)) window.close();
        if (this.inputListener.isKeyPressed(GLFW_KEY_ESCAPE)) this.paused = !this.paused;
        if (this.paused) {
            this.mainMenu.draw(this.camera);
        } else {
            this.scene.draw(delta);
        }
    }
}
