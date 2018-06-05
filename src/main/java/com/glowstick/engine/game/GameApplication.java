package com.glowstick.engine.game;

import com.glowstick.engine.EngineModule;
import com.glowstick.engine.graphics.Window;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(EngineModule.class)
@SpringBootApplication
public class GameApplication {
	public static void main(String[] args) {
		SpringApplication.run(GameApplication.class, args);
	}

	@Bean
    public Window window() {
	    return new Window("test", 800, 600);
    }
}
