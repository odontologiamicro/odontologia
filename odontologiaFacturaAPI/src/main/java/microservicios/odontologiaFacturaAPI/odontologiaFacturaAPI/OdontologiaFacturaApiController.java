package microservicios.odontologiaFacturaAPI.odontologiaFacturaAPI;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import microservicios.odontologia.modelo.Factura;
import microservicios.odontologia.util.OdontologiaUtil;
import microservicios.odontologiaFacturaAPI.odontologiaFacturaAPI.rabbitconf.Publicador;

@RestController
public class OdontologiaFacturaApiController {

	Publicador publicador = new Publicador();	
	
	  @RequestMapping(method = RequestMethod.POST, value = "/factura")
	  public ResponseEntity<Factura> crearRegalo(@RequestBody Factura factura) throws IOException{
	    publicador.publicarMensajeAsnc("miroservicios.odontologia.citaagendada", "miroservicios.odontologia.citaagendada.facturarcita", OdontologiaUtil.serialize(factura));
	    return new ResponseEntity<Factura>(HttpStatus.OK);
	  }
}
