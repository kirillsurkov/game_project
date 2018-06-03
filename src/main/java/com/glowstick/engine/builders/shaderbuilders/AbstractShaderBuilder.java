package com.glowstick.engine.builders.shaderbuilders;

import com.glowstick.engine.graphics.Shader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

import static org.lwjgl.opengl.GL20.*;

public abstract class AbstractShaderBuilder<T extends Shader> {
    @Autowired
    private ResourceLoader resourceLoader;

    abstract protected T build(int program);
    abstract public String getName();

    private String loadSource(String shaderName, String fileName) throws IOException {
        Resource res = resourceLoader.getResource("classpath:shaders/" + shaderName + "/" + fileName);
        FileReader fileReader = new FileReader(res.getFile());
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        return String.join("\n", bufferedReader.lines().collect(Collectors.toList()));
    }

    public T build() throws IOException {
        String vShaderSrc = loadSource(this.getName(), "vertex.glsl");
        int vShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vShader, vShaderSrc);
        glCompileShader(vShader);

        String fShaderSrc = loadSource(this.getName(), "fragment.glsl");
        int fShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fShader, fShaderSrc);
        glCompileShader(fShader);

        int program = glCreateProgram();
        glAttachShader(program, vShader);
        glAttachShader(program, fShader);
        glLinkProgram(program);

        return this.build(program);
    }
}
