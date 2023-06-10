package kr.co.hrmanager.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "User API",
                version = "${api.version}",
                license = @License(
                        name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"
                ),
                description = "${api.description}"
        ),
        servers = @Server(
                url = "${api.server.url}",
                description = "${api.server.description}"
        ),
        security = @SecurityRequirement(name = "Bearer Authentication")
)
public class OpenApiSecurityConfig {
}
