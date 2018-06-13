package com.glowstick.game.core;

import com.glowstick.engine.game.Scene;
import com.glowstick.engine.game.camera.Camera3D;
import com.glowstick.game.scenes.Scene01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Q;

@Component
public class Game extends com.glowstick.engine.game.Game {
    @Autowired
    private Camera3D camera;
    private MainMenu mainMenu;
    private Scene scene;
    private boolean paused = true;
    private float timer = 0;
    private int frames = 0;

    @Override
    protected void init() throws Exception {
        this.mainMenu = new MainMenu(this.entityBuilder);
        this.scene = new Scene01(this.entityBuilder, this.camera);
    }

    @Override
    public void draw(double delta) {
        this.timer += delta;
        this.frames++;
        if (this.timer >= 1) {
            System.out.println("FPS: " + (frames / timer));
            this.timer = 0;
            this.frames = 0;
        }
        if (this.inputListener.isKeyDown(GLFW_KEY_Q)) this.window.close();
        if (this.inputListener.isKeyPressed(GLFW_KEY_ESCAPE)) this.paused = !this.paused;
        if (this.paused) {
            this.mainMenu.draw();
        } else {
            this.scene.draw(delta);
        }
    }
}
