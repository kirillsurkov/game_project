package com.glowstick.engine.graphics;

import com.glowstick.engine.service.Window;
import lombok.Setter;
import org.lwjgl.opengl.GL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public Graphics() {
        GL.createCapabilities();
    }

    public void loop() {
        glClearColor(0, 0, 0, 1);
        while (!glfwWindowShouldClose(this.window.getId())) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            this.onDraw.accept(0.0);
            glfwSwapBuffers(this.window.getId());
            glfwPollEvents();
        }
    }

    public void setClearColor(float r, float g, float b) {
        glClearColor(r, g, b, 1);
    }

    public void drawText(String text, int x, int y) {
    }
}
