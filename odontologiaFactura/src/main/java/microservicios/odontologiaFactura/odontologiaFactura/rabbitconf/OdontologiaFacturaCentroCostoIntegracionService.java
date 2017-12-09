package microservicios.odontologiaFactura.odontologiaFactura.rabbitconf;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import microservicios.odontologiaFacturaAPI.odontologiaFacturaAPI.modelo.CentroCosto;

@Service
public class OdontologiaFacturaCentroCostoIntegracionService {

	private final RestTemplate restTemplate;

    public OdontologiaFacturaCentroCostoIntegracionService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
	private String uri = "http://localhost:8080/odontologiamicro/OdontologiaCentroCostosAPI/1.0.0/centroCosto/{idCentroCostoParam}:";
	
	@HystrixCommand(fallbackMethod = "getCentroCostoByIdDefault")
    public CentroCosto getCentroCostoById (String idCentroCosto) {    
         
        return restTemplate.getForObject(uri,CentroCosto.class, idCentroCosto);     
    }
     
    public CentroCosto getCentroCostoByIdDefault(String name) {
        CentroCosto centro = new CentroCosto();
        centro.setDescripcion("Hello World thanks to Circuit Breaker (Hystrix)");
        return centro;
     }
}
