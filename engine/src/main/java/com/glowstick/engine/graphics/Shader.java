package com.glowstick.engine.graphics;

import com.glowstick.engine.extension.Cacheable;
import com.glowstick.engine.game.camera.Camera;
import com.glowstick.engine.game.Entity;
import lombok.Getter;
import lombok.Setter;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL20.*;

public abstract class Shader implements Cacheable {
    @Getter
    private final String name;
    private final int program;
    @Setter
    private boolean enabled = true;
    @Setter
    private int width;
    @Setter
    private int height;

    public Shader(String name, int program) {
        this.name = name;
        this.program = program;
    }

    public void linkAttributes() {
        int posAttrib = this.getAttribLocation("position");
        glVertexAttribPointer(posAttrib, 3, GL_FLOAT, false, 32, 0);
        glEnableVertexAttribArray(posAttrib);
        int normalAttrib = this.getAttribLocation("normal");
        glVertexAttribPointer(normalAttrib, 3, GL_FLOAT, false, 32, 12);
        glEnableVertexAttribArray(normalAttrib);
        int texCoordAttrib = this.getAttribLocation("texCoord");
        glVertexAttribPointer(texCoordAttrib, 2, GL_FLOAT, false, 32, 24);
        glEnableVertexAttribArray(texCoordAttrib);
    }

    abstract protected void linkUniforms(Camera camera, Entity entity);

    protected int getAttribLocation(String attrib) {
        return glGetAttribLocation(this.program, attrib);
    }

    protected int getUniformLocation(String uniform) {
        return glGetUniformLocation(this.program, uniform);
    }

    public void bind(Camera camera, Entity entity) {
        glUseProgram(this.program);
        this.linkUniforms(camera, entity);
        glUniform1i(this.getUniformLocation("enabled"), this.enabled ? 1 : 0);
        glUniform2f(this.getUniformLocation("resolution"), this.width, this.height);

    }
}
