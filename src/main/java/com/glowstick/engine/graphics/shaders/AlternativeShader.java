package com.glowstick.engine.graphics.shaders;

import com.glowstick.engine.graphics.Shader;
import com.glowstick.engine.service.Entity;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL20.*;

public class AlternativeShader extends Shader {
    public AlternativeShader(String name, int program) {
        super(name, program);
    }

    @Override
    public void linkAttributes() {
        int posAttrib = this.getAttribLocation("position");
        glVertexAttribPointer(posAttrib, 2, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(posAttrib);
    }

    @Override
    public void linkUniforms(Entity entity) {
        Vector3f color = entity.getColor();
        glUniform3f(this.getUniformLocation("uColor"), color.x, color.y, color.z);
        Vector3f coords = entity.getCoords();
        glUniform3f(this.getUniformLocation("uOffset"), coords.x, coords.y, coords.z);
        glUniform1f(this.getUniformLocation("uScale"), entity.getScale());
    }
}
