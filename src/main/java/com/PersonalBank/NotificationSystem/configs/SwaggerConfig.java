package com.PersonalBank.NotificationSystem.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Value("${springdoc.swagger-ui.server}")
    private String serverUrl;

    @Bean
    public OpenAPI apiInfo() {
        var server = new Server();
        server.setUrl(serverUrl);
        return new OpenAPI()
                .addServersItem(server)
                .info(new Info()
                        .title("")
                        .description("Bank-notification-system")
                        .version("0.1"));
    }
}
