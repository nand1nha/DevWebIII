package locadora.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI locadoraOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Locadora")
                        .description("API REST para gerenciamento do acervo da Locadora " +
                                     "(Atores, Diretores, Classes, Títulos e Itens).")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Fernanda")
                                .email("fsmartinelli03@gmail.com.com"))
                        .license(new License()
                                .name("Uso acadêmico")
                                .url("https://exemplo.com")));
    }
}