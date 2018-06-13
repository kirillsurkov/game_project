package com.glowstick.engine.builders;

import com.glowstick.engine.graphics.Model;
import com.glowstick.engine.geometry.Vertex;
import org.lwjgl.util.vector.Vector2f;
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

    @Override
    public Model build(String name) throws Exception {
        List<Vertex> vertices = new ArrayList<>();
        List<Vector3f> points = new ArrayList<>();
        List<Vector3f> normals = new ArrayList<>();
        List<Vector2f> texCoords = new ArrayList<>();
        Resource resource = resourceLoader.getResource("classpath:models/" + name + "/model.obj");
        InputStreamReader streamReader = new InputStreamReader(resource.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        bufferedReader.lines().forEach(line -> {
            String[] data = line.split(" ");
            for(String op : data) {
                if ("v".equals(op)) {
                    points.add(new Vector3f(
                            Float.valueOf(data[1]),
                            Float.valueOf(data[2]),
                            Float.valueOf(data[3])
                    ));
                }
                if ("vn".equals(op)) {
                    normals.add(new Vector3f(
                            Float.valueOf(data[1]),
                            Float.valueOf(data[2]),
                            Float.valueOf(data[3])
                    ));
                }
                if ("vt".equals(op)) {
                    texCoords.add(new Vector2f(
                            Float.valueOf(data[1]),
                            Float.valueOf(data[2])
                    ));
                }
                if ("f".equals(op)) {
                    for (int i = 1; i < data.length; i++) {
                        String[] face = data[i].split("/");
                        Vector3f point = face[0].isEmpty() ? null : points.get(Integer.parseInt(face[0])-1);
                        Vector3f normal = face[2].isEmpty() ? null : normals.get(Integer.parseInt(face[2])-1);
                        Vector2f texCoord = face[1].isEmpty() ? null : texCoords.get(Integer.parseInt(face[1])-1);
                        vertices.add(new Vertex(
                                point == null ? new Vector3f() : point,
                                normal == null ? new Vector3f() : normal,
                                texCoord == null ? new Vector2f() : texCoord
                        ));
                    }
                }
            }
        });
        float[] rawVertices = new float[vertices.size() * 8];
        for (int i = 0; i < vertices.size(); i++) {
            Vertex vertex = vertices.get(i);
            rawVertices[i*8]   = vertex.getCoodrs().getX();
            rawVertices[i*8+1] = vertex.getCoodrs().getY();
            rawVertices[i*8+2] = vertex.getCoodrs().getZ();
            rawVertices[i*8+3] = vertex.getNormal().getX();
            rawVertices[i*8+4] = vertex.getNormal().getY();
            rawVertices[i*8+5] = vertex.getNormal().getZ();
            rawVertices[i*8+6] = vertex.getTexCoords().getX();
            rawVertices[i*8+7] = vertex.getTexCoords().getY();
        }
        return new Model(name, rawVertices, vertices.size());
    }
}
