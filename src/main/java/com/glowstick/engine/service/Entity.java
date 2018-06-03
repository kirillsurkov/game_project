package com.glowstick.engine.service;

import com.glowstick.engine.caches.ModelCache;
import com.glowstick.engine.caches.ShaderCache;
import com.glowstick.engine.graphics.Model;
import com.glowstick.engine.graphics.Shader;
import javafx.util.Pair;
import lombok.Getter;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
    private ModelCache modelCache;
    private ShaderCache shaderCache;
    private List<Pair<Model, Shader>> models = new ArrayList<>();
    @Getter
    private float scale = 1.0f;
    @Getter
    private Vector3f coords = new Vector3f();
    @Getter
    private Vector3f color = new Vector3f();
    private Matrix4f modelMatrix = new Matrix4f();
    private Matrix4f viewMatrix = new Matrix4f();
    private Matrix4f projectionMatrix = new Matrix4f();

    public Entity(ModelCache modelCache, ShaderCache shaderCache) {
        this.modelCache = modelCache;
        this.shaderCache = shaderCache;
    }

    protected void addModel(String modelName, String shaderName) throws Exception {
        this.models.add(new Pair<>(this.modelCache.get(modelName), shaderCache.get(shaderName)));
    }

    public Entity scale(float scale) {
        this.scale = scale;
        return this;
    }

    public Entity color(float r, float g, float b) {
        this.color.x = r;
        this.color.y = g;
        this.color.z = b;
        return this;
    }

    public Entity move(float x, float y) {
        this.coords.x += x;
        this.coords.y += y;
        return this;
    }

    public void draw() {
        this.models.forEach(pair -> {
            pair.getKey().draw(pair.getValue(), this);
        });
    }

    abstract public void update(double delta);
}
