package com.glowstick.engine.builders;

import com.glowstick.engine.caches.ShaderCache;
import com.glowstick.engine.geometry.Point;
import com.glowstick.engine.geometry.Vertex;
import com.glowstick.engine.graphics.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
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
        List<Point> points = new ArrayList<>();
        Resource resource = resourceLoader.getResource("classpath:models/" + name + "/model.obj");
        InputStreamReader streamReader = new InputStreamReader(resource.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        bufferedReader.lines().forEach(line -> {
            String[] data = line.split(" ");
            for(String op : data) {
                if ("v".equals(op)) {
                    points.add(new Point(
                            Double.valueOf(data[1]),
                            Double.valueOf(data[3])
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
        return new Model(name, this.shaderCache.get("default"), vertices);
    }
}
