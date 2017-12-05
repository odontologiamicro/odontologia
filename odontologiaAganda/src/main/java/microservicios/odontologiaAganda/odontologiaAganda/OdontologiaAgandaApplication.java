package microservicios.odontologiaAganda.odontologiaAganda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OdontologiaAgandaApplication {

	public static void main(String[] args) {
		SpringApplication.run(OdontologiaAgandaApplication.class, args);
	}
}
