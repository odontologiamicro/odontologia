package microservicios.odontologiaAgendaAPI.odontologiaAgendaAPI;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import microservicios.odontologiaAgendaAPI.odontologiaAgendaAPI.modelo.Cita;
import microservicios.odontologiaAgendaAPI.odontologiaAgendaAPI.rabbitconfig.Publicador;

@RestController
public class OdontologiaAgendaAPIContoller {
	Publicador publicador = new Publicador();

	public static byte[] serialize(Object obj) throws IOException {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(obj);
	    return out.toByteArray();
	}
	
	  @RequestMapping(method = RequestMethod.POST, value = "/cita")
	  public ResponseEntity<Cita> crearRegalo(@RequestBody Cita cita) throws IOException{
	    publicador.publicarMensajeAsnc("miroservicios.odontologia.citaagendada", "miroservicios.odontologia.citaagendada.agendar", serialize(cita));
	    return new ResponseEntity<Cita>(HttpStatus.OK);
	  }
}
