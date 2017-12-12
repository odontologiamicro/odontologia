package microservicios.odontologiaAppMovilAPI.odontologiaAppMovilAPI;

import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import microservicios.odontologia.modelo.Cita;
import microservicios.odontologia.util.PeticionAgendaDTO;
import microservicios.odontologia.util.TipoConsulta;
import microservicios.odontologiaAppMovilAPI.odontologiaAppMovilAPI.rabbitconfig.Publicador;
import static microservicios.odontologiaAppMovilAPI.odontologiaAppMovilAPI.rabbitconfig.RabbitConfig.*;

@RestController
public class OdontologiaAgendaAPIContoller {
	Publicador publicador = new Publicador();	
	
	  @RequestMapping(method = RequestMethod.POST, value = "/agendarCita")
	  public ResponseEntity<Cita> agendarCita(@RequestBody PeticionAgendaDTO agendaCita) throws IOException{
		  agendaCita.setTipoConsulta(TipoConsulta.AGENDAR_CITA);
		  publicador.send(ROUTING_KEY_CREATE_NAME, agendaCita);
		  return new ResponseEntity<Cita>(HttpStatus.OK);
	  }
	  
	  @RequestMapping(method = RequestMethod.POST, value = "/consultarCita")
	  public ResponseEntity<List<Cita>> consultarCita(@RequestBody PeticionAgendaDTO consultaPorPaciente) throws IOException{
		  consultaPorPaciente.setTipoConsulta(TipoConsulta.CONSULTAR_CITA_POR_PACIENTE);		  
		  publicador.send(ROUTING_KEY_CONSULT_NAME, consultaPorPaciente);
	    return new ResponseEntity<List<Cita>>(HttpStatus.OK);
	  }	  
}
