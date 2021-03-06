package microservicios.odontologiaFactura.odontologiaFactura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import microservicios.odontologia.modelo.CentroCosto;

@EnableCircuitBreaker
@RestController
public class OdontologiaFacturaCentroCostoIntegracionController {

	@Autowired
	private final OdontologiaFacturaCentroCostoIntegracionService myAppService;

    public OdontologiaFacturaCentroCostoIntegracionController(OdontologiaFacturaCentroCostoIntegracionService myAppService) {
        this.myAppService = myAppService;
    }
    
    @RequestMapping(value = "/consultaCentroCosto/{idCentroCosto}", method = RequestMethod.GET)
    public CentroCosto getCentroCostoById(@PathVariable String idCentroCosto) {
        return myAppService.getCentroCostoById(idCentroCosto);
    }
    
}
