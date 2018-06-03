package com.glowstick.engine;

import com.glowstick.engine.service.Window;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
