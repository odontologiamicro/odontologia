package microservicios.odontologiaFactura.odontologiaFactura;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import microservicios.odontologia.modelo.CentroCosto;
import microservicios.odontologia.modelo.UnidadDeNegocio;

@Service
public class OdontologiaFacturaCentroCostoIntegracionService {

	private final RestTemplate restTemplate;
	private String uri = "http://localhost:8080/odontologiamicro/OdontologiaCentroCostosAPI/1.0.0/centroCosto/{idCentroCostoParam}:";

    public OdontologiaFacturaCentroCostoIntegracionService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
	
	@HystrixCommand(fallbackMethod = "getCentroCostoByIdDefault")
    public CentroCosto getCentroCostoById (String idCentroCosto) {    
         
        return restTemplate.getForObject(uri,CentroCosto.class, idCentroCosto);     
    }
     
    public CentroCosto getCentroCostoByIdDefault(String name) {
    		UnidadDeNegocio unidad = new UnidadDeNegocio();
    		unidad.setEstado("ACT");
    		unidad.setNombreEmpresa("Odontologia");
    		unidad.setIdUnidadDeNegocio("UNG");
    		unidad.setNombre("Unidad de Negocio General");
        CentroCosto centro = new CentroCosto();
        centro.setUnidadDeNegocio(unidad);
        centro.setDescripcion("Centro por defecto Circuit Breaker (Hystrix)");
        centro.setEstado("ACT");
        centro.setIdCentroCosto("CCG");
        centro.setNombre("Centro Costo por defecto");
        return centro;
     }
}
