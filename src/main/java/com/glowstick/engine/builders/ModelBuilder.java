package com.glowstick.engine.builders;

import com.glowstick.engine.caches.ShaderCache;
import com.glowstick.engine.geometry.Vertex;
import com.glowstick.engine.graphics.Model;
import org.lwjgl.util.vector.Vector3f;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class ModelBuilder implements Builder<Model> {
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ShaderCache shaderCache;

    @Override
    public Model build(String name) throws Exception {
        List<Vertex> vertices = new ArrayList<>();
        List<Vector3f> points = new ArrayList<>();
        Resource resource = resourceLoader.getResource("classpath:models/" + name + "/model.obj");
        InputStreamReader streamReader = new InputStreamReader(resource.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        bufferedReader.lines().forEach(line -> {
            String[] data = line.split(" ");
            for(String op : data) {
                if ("v".equals(op)) {
                    points.add(new Vector3f(
                            Float.valueOf(data[1]),
                            Float.valueOf(data[3]),
                            0
                    ));
                }
                if ("f".equals(op)) {
                    for (int i = 1; i < data.length; i++) {
                        String[] face = data[i].split("/");
                        vertices.add(new Vertex(
                                points.get(Integer.valueOf(face[0])-1)
                        ));
                    }
                }
            }
        });
        float[] rawVertices = new float[vertices.size() * 2];
        for (int i = 0; i < vertices.size(); i++) {
            rawVertices[i*2] = vertices.get(i).getCoodrs().getX();
            rawVertices[i*2+1] = vertices.get(i).getCoodrs().getY();
        }
        return new Model(name, rawVertices, vertices.size());
    }
}
