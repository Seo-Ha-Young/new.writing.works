package writing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import writing.server.config.FileStorageProperties;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties(FileStorageProperties.class)
public class WritingApplication {

	public static void main(String[] args) {
		SpringApplication.run(WritingApplication.class, args);
	}

}
