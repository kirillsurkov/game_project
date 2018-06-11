package com.glowstick.engine.graphics;

import com.glowstick.engine.game.InputListener;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.createCapabilities;

public class Window {
    private String title;
    private int width;
    private int height;
    private long id;

    @Autowired
    private InputListener inputListener;

    public Window(String title, int width, int height) throws Exception {
        this.title = title;
        this.width = width;
        this.height = height;

        if (!glfwInit()) throw new Exception("GLFW init failed");
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 0);
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        this.id = glfwCreateWindow(this.width, this.height, this.title, 0, 0);
        if (this.id == 0) throw new Exception("GLFW window creation failed");
        glfwMakeContextCurrent(this.id);
        glfwSwapInterval(1);
        glfwShowWindow(this.id);
        createCapabilities();
    }

    @PostConstruct
    private void init() {
        glfwSetKeyCallback(this.id, this.inputListener::onKeyboard);
        glfwSetMouseButtonCallback(this.id, this.inputListener::onMouse);
        glfwSetCursorPosCallback(this.id, this.inputListener::onCursor);
    }

    public void close() {
        glfwSetWindowShouldClose(this.id, true);
    }

    public void swapBuffers() {
        glfwSwapBuffers(this.id);
    }

    public boolean isAlive() {
        return !glfwWindowShouldClose(this.id);
    }

    @PreDestroy
    private void destroy() {
        glfwDestroyWindow(this.id);
        glfwTerminate();
    }
}
