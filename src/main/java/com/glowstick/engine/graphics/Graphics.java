package com.glowstick.engine.graphics;

import com.glowstick.engine.service.Window;
import lombok.Setter;
import org.lwjgl.opengl.GL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.function.Consumer;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.*;

@Component
public class Graphics {
    @Autowired
    private Window window;
    @Setter
    private Consumer<Double> onDraw;

    @Autowired
    private Fbo fbo;

    public Graphics() {
        GL.createCapabilities();
    }

    @PostConstruct
    private void init()  {
        glDepthFunc(GL_LESS);
        glEnable(GL_DEPTH_TEST);
    }

    public void loop() {
        double oldTime = System.nanoTime() / 1000000000.0;
        glClearColor(0, 0, 0, 1);

        while (!glfwWindowShouldClose(this.window.getId())) {
            double time = System.nanoTime() / 1000000000.0;
            this.fbo.bind();
            this.onDraw.accept(time - oldTime);
            this.fbo.unbind();
            this.fbo.draw();
            glfwSwapBuffers(this.window.getId());
            glfwPollEvents();
            oldTime = time;
        }
    }

    public void setClearColor(float r, float g, float b) {
        glClearColor(r, g, b, 1);
    }
}
