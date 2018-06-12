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
    }

    public void loop() {
        double oldTime = System.nanoTime() / 1000000000.0;
        glClearColor(0, 0, 0, 1);

        while (this.window.isAlive()) {
            double time = System.nanoTime() / 1000000000.0;
            this.fbo.bind();
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glEnable(GL_DEPTH_TEST);
            this.game.draw(time - oldTime);
            this.fbo.unbind();
            glClear(GL_COLOR_BUFFER_BIT);
            glDisable(GL_DEPTH_TEST);
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
