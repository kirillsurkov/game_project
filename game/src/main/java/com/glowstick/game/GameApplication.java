package com.glowstick.game;

import com.glowstick.engine.EngineModule;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(EngineModule.class)
public class GameApplication {
	public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(GameApplication.class);
        builder.headless(false).run(args);
	}
}
