package microservicios.odontologiaFacturaAPI.odontologiaFacturaAPI;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import microservicios.odontologiaFacturaAPI.odontologiaFacturaAPI.modelo.Factura;
import microservicios.odontologiaFacturaAPI.odontologiaFacturaAPI.rabbitconf.Publicador;

@RestController
public class OdontologiaFacturaApiController {

	Publicador publicador = new Publicador();

	public static byte[] serialize(Object obj) throws IOException {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(obj);
	    return out.toByteArray();
	}
	
	  @RequestMapping(method = RequestMethod.POST, value = "/factura")
	  public ResponseEntity<Factura> crearRegalo(@RequestBody Factura factura) throws IOException{
	    publicador.publicarMensajeAsnc("miroservicios.odontologia.citaagendada", "miroservicios.odontologia.citaagendada.facturarcita", serialize(factura));
	    return new ResponseEntity<Factura>(HttpStatus.OK);
	  }
}
