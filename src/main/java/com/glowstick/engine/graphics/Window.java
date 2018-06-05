package com.glowstick.engine.graphics;

import com.glowstick.engine.InputListener;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.lwjgl.glfw.GLFW.*;

public class Window {
    private String title;
    private int width;
    private int height;
    @Getter
    private long id;

    @Autowired
    private InputListener inputListener;

    public Window(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public void close() {
        glfwSetWindowShouldClose(this.id, true);
    }

    @PostConstruct
    private void init() throws Exception {
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
        glfwSetKeyCallback(this.id, inputListener::onKeyboard);
        glfwSetMouseButtonCallback(this.id, inputListener::onMouse);
        glfwSetCursorPosCallback(this.id, inputListener::onCursor);
    }

    @PreDestroy
    private void destroy() {
        glfwDestroyWindow(this.id);
        glfwTerminate();
    }
}
