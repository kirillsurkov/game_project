package com.glowstick.engine.game;

import com.glowstick.engine.graphics.Model;
import com.glowstick.engine.graphics.Shader;
import com.glowstick.engine.cache.ModelCache;
import com.glowstick.engine.cache.ShaderCache;
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
    private Matrix4f modelMatrix = new Matrix4f();
    @Getter
    private Vector3f color = new Vector3f();

    public Entity(ModelCache modelCache, ShaderCache shaderCache) {
        this.modelCache = modelCache;
        this.shaderCache = shaderCache;
    }

    protected void addModel(String modelName, String shaderName) throws Exception {
        this.models.add(new Pair<>(this.modelCache.get(modelName), this.shaderCache.get(shaderName)));
    }

    public Entity scale(float scale) {
        this.modelMatrix.scale(new Vector3f(scale, scale, scale));
        return this;
    }

    public Entity color(float r, float g, float b) {
        this.color.x = r;
        this.color.y = g;
        this.color.z = b;
        return this;
    }

    public Entity move(float x, float y, float z) {
        this.modelMatrix.translate(new Vector3f(x, y, z));
        return this;
    }

    public Entity rotate(float angle, Vector3f axis) {
        this.modelMatrix.rotate(angle, axis);
        return this;
    }

    public void draw(Camera camera) {
        this.models.forEach(pair ->
                pair.getKey().draw(pair.getValue(), camera, this)
        );
    }

    abstract public void update(double delta);
}
