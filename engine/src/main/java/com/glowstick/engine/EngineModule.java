package com.glowstick.engine;

import com.glowstick.engine.graphics.Window;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
public class EngineModule {
    @Bean
    public Window window() throws Exception {
        int width = 1280;
        int height = 720;
        return new Window("test", width, height, false);
    }
}
