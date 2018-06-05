package com.glowstick.engine.builders;

import com.glowstick.engine.graphics.Texture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class TextureBuilder implements Builder<Texture> {
    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public Texture build(String name) throws Exception {
        Resource resource = this.resourceLoader.getResource(name);
        InputStream inputStream = resource.getInputStream();
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        throw new Exception("Method not supported");
    }
}
