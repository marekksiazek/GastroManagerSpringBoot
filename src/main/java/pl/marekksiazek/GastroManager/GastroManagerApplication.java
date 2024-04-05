package pl.marekksiazek.GastroManager;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Gastro Manager APIS", version = "1.0", description = "Gastro Manager API " +
		"Documentation"))
public class GastroManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GastroManagerApplication.class, args);
	}

}
