package com.slowv.fruit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Configuration
public class EmailConfig {

    @Bean
    public SpringTemplateEngine templateEngine() {
        final var templateEngine = new SpringTemplateEngine();
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }
}
