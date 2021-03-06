package microservicios.odontologiaAgendaAPI.odontologiaAgendaAPI;

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
import microservicios.odontologiaAgendaAPI.odontologiaAgendaAPI.rabbitconfig.Publicador;
import static microservicios.odontologiaAgendaAPI.odontologiaAgendaAPI.rabbitconfig.RabbitConfig.*;

@RestController
public class OdontologiaAgendaAPIContoller {
	
	Publicador publicador = new Publicador();	
	
	  @RequestMapping(method = RequestMethod.POST, value = "/citaPorMedico")
	  public ResponseEntity<List<Cita>> consultarCitaPorMedico(@RequestBody PeticionAgendaDTO consultaPorMedico) throws IOException{		  
		  consultaPorMedico.setTipoConsulta(TipoConsulta.CONSULTAR_CITA_POR_MEDICO);
		  List<Cita> lstCitas = publicador.publicarMensajeSnc( ROUTING_KEY_NAME, consultaPorMedico);
		  return new ResponseEntity<List<Cita>>(lstCitas, HttpStatus.OK);
	  }
	  
	  @RequestMapping(method = RequestMethod.POST, value = "/citaPorPaciente")
	  public ResponseEntity<List<Cita>> consultarCitaPorPaciente(@RequestBody PeticionAgendaDTO consultaPorPaciente) throws IOException{
		  consultaPorPaciente.setTipoConsulta(TipoConsulta.CONSULTAR_CITA_POR_PACIENTE);
		  List<Cita> lstCitas = publicador.publicarMensajeSnc( ROUTING_KEY_NAME, consultaPorPaciente);
	    return new ResponseEntity<List<Cita>>(lstCitas, HttpStatus.OK);
	  }
}
