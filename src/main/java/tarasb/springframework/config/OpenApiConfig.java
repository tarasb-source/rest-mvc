package tarasb.springframework.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
//                .info(new Info()
//                        .title("Spring Framework API")
//                        .version("1.0")
//                        .description("API documentation for the Spring application")
//                        .contact(new Contact()
//                                .name("Taras")
//                                .email("taras.brytskiy@gmail.com")
//                                .url("https://github.com/tarasb-source")));
                .info(metaData());
    }

    private Info metaData() {
        Contact contact = new Contact();
        contact.name("Taras Brytskyy");
        contact.email("taras.brytskiy@gmail.com");
        contact.url("https://github.com/tarasb-source");

        Info info = new Info();
        info.title("Spring Framework API");
        info.version("1.0");
        info.description("API documentation for the Spring application");
        info.contact(contact);

        return info;
    }
}
