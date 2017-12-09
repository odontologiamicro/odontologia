package microservicios.odontologiaAppMovilAPI.odontologiaAppMovilAPI;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import microservicios.odontologia.modelo.Cita;
import microservicios.odontologia.util.OdontologiaUtil;
import microservicios.odontologiaAppMovilAPI.odontologiaAppMovilAPI.rabbitconfig.Publicador;

@RestController
public class OdontologiaAgendaAPIContoller {
	Publicador publicador = new Publicador();	
	
	  @RequestMapping(method = RequestMethod.POST, value = "/cita")
	  public ResponseEntity<Cita> agendarCita(@RequestBody Cita cita) throws IOException{
	    publicador.publicarMensajeAsnc("miroservicios.odontologia.citaagendada", "miroservicios.odontologia.citaagendada.agendar", OdontologiaUtil.serialize(cita));
	    return new ResponseEntity<Cita>(HttpStatus.OK);
	  }
}
