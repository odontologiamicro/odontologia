package microservicios.odontologiaFacturaAPI.odontologiaFacturaAPI;

import static microservicios.odontologiaFacturaAPI.odontologiaFacturaAPI.rabbitconf.RabbitConfig.*;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import microservicios.odontologia.modelo.Cita;
import microservicios.odontologia.modelo.Factura;
import microservicios.odontologia.util.OdontologiaUtil;
import microservicios.odontologia.util.PeticionFacturaDTO;
import microservicios.odontologia.util.TipoConsulta;
import microservicios.odontologiaFacturaAPI.odontologiaFacturaAPI.rabbitconf.Publicador;

@RestController
public class OdontologiaFacturaApiController {

	Publicador publicador = new Publicador();	  
	  
	  @RequestMapping(method = RequestMethod.POST, value = "/consultarFactura")
	  public ResponseEntity<Factura> consultarFactura(@RequestBody PeticionFacturaDTO codigoCita) throws IOException{
		  codigoCita.setTipoPeticion(TipoConsulta.CONSULTAR_FACTURA);
		  Factura fact = publicador.publicarMensajeSnc(QUEUE_FACTURA_CONSULTADA_ROUTING_KEY_NAME, codigoCita);
		 // publicador.send(consultaFactura);
	    return new ResponseEntity<Factura>(fact, HttpStatus.OK);
	  }
}
