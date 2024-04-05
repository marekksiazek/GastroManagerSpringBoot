package pl.marekksiazek.GastroManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan
@EntityScan(basePackages = {"pl.marekksiazek.GastroManager.entity"})
@EnableJpaRepositories(basePackages = {"pl.marekksiazek.GastroManager.repository"})
public class GastroManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GastroManagerApplication.class, args);
	}

}
