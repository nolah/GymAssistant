package ninja.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import ninja.backend.config.CustomProperties;


@ComponentScan("ninja.backend")
@EnableAutoConfiguration
@EnableConfigurationProperties({LiquibaseProperties.class, CustomProperties.class})
public class BackendApplication {

    public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(BackendApplication.class);
        final SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
        if (!source.containsProperty("spring.profiles.active") && !System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {
            app.setAdditionalProfiles("dev");
        }
        app.run(args).getEnvironment();
    }

}
