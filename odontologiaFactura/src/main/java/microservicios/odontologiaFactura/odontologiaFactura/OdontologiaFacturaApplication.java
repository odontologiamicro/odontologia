package microservicios.odontologiaFactura.odontologiaFactura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OdontologiaFacturaApplication {

	public static void main(String[] args) {
		SpringApplication.run(OdontologiaFacturaApplication.class, args);
	}
}
