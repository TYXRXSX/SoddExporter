package by.gago.exporter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ExporterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExporterApplication.class, args);
	}

}
