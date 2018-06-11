package com.glowstick.engine.graphics;

import com.glowstick.engine.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.opengl.GL11.*;

@Component
public class Graphics {
    @Autowired
    private Window window;
    @Autowired
    private Game game;

    @Autowired
    private Fbo fbo;

    @PostConstruct
    private void init()  {
        glDepthFunc(GL_LESS);
        glEnable(GL_DEPTH_TEST);
    }

    public void loop() {
        double oldTime = System.nanoTime() / 1000000000.0;
        glClearColor(0, 0, 0, 1);

        while (this.window.isAlive()) {
            double time = System.nanoTime() / 1000000000.0;
            this.fbo.bind();
            this.game.update(time - oldTime);
            this.fbo.unbind();
            this.fbo.draw();
            this.window.swapBuffers();
            glfwPollEvents();
            oldTime = time;
        }
    }

    public void setClearColor(float r, float g, float b) {
        glClearColor(r, g, b, 1);
    }
}
