package com.glowstick.engine;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

@Component
public class InputListener {
    private Map<Integer, Boolean> keyboard = new HashMap<>();
    private Map<Integer, Boolean> keyboardPressed = new HashMap<>();
    private Map<Integer, Boolean> mouse = new HashMap<>();
    @Getter
    private double mouseX;
    @Getter
    private double mouseY;

    public void onKeyboard(long window, int key, int scancode, int action, int mods) {
        this.keyboard.put(key, action == GLFW_PRESS);
        this.keyboardPressed.put(key, action == GLFW_PRESS);
    }

    public void onMouse(long window, int button, int action, int mods) {
        this.mouse.put(button, action == GLFW_PRESS);
    }

    public void onCursor(long window, double x, double y) {
        this.mouseX = x;
        this.mouseY = y;
    }

    public boolean isKeyDown(int glfwKey) {
        return this.keyboard.getOrDefault(glfwKey, false);
    }

    public boolean isKeyPressed(int glfwKey) {
        boolean res = this.keyboardPressed.getOrDefault(glfwKey, false);
        this.keyboardPressed.put(glfwKey, false);
        return res;
    }

    public boolean isMouseButtonPressed(boolean rightButton) {
        if (rightButton) return this.mouse.get(GLFW_MOUSE_BUTTON_1);
        return this.mouse.get(GLFW_MOUSE_BUTTON_2);
    }
}
