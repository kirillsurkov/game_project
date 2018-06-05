package com.glowstick.engine;

import com.glowstick.engine.graphics.Window;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
public class EngineModule {
    @Bean
    public Window window() {
        return new Window("test", 800, 600);
    }
}
