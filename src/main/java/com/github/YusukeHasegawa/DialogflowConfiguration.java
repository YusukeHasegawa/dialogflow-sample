package com.github.YusukeHasegawa;

import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ai.api.AIConfiguration;
import ai.api.AIDataService;

@Configuration
public class DialogflowConfiguration {

	@Bean
	AIConfiguration aIConfiguration(ApplicationArguments arguments) {
		AIConfiguration configuration = new AIConfiguration(arguments.getSourceArgs()[0]);
		return configuration;
	};

	@Bean
	AIDataService dataService(AIConfiguration aIConfiguration) {
		AIDataService dataService = new AIDataService(aIConfiguration);
		return dataService;

	}

}
