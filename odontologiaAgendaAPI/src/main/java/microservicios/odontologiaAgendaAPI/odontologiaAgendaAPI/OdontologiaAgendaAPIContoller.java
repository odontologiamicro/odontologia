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
import microservicios.odontologia.modelo.Medico;
import microservicios.odontologia.modelo.Paciente;
import microservicios.odontologia.util.PeticionAgendaDTO;
import microservicios.odontologia.util.OdontologiaUtil;
import microservicios.odontologia.util.TipoConsulta;
import microservicios.odontologiaAgendaAPI.odontologiaAgendaAPI.rabbitconfig.Publicador;

@RestController
public class OdontologiaAgendaAPIContoller {
	Publicador publicador = new Publicador();	
	
	  @RequestMapping(method = RequestMethod.POST, value = "/citaPorMedico")
	  public ResponseEntity<List<Cita>> consultarCitaPorMedico(@RequestBody PeticionAgendaDTO consultaPorMedico) throws IOException{		  
		  consultaPorMedico.setTipoConsulta(TipoConsulta.CONSULTAR_CITA_POR_MEDICO);
		  publicador.publicarMensajeAsnc("miroservicios.odontologia.citaagendada", "miroservicios.odontologia.citaagendada.consultarcita", OdontologiaUtil.serialize(consultaPorMedico));
	    return new ResponseEntity<List<Cita>>(HttpStatus.OK);
	  }
	  
	  @RequestMapping(method = RequestMethod.POST, value = "/citaPorPaciente")
	  public ResponseEntity<List<Cita>> consultarCitaPorPaciente(@RequestBody PeticionAgendaDTO consultaPorPaciente) throws IOException{
		  consultaPorPaciente.setTipoConsulta(TipoConsulta.CONSULTAR_CITA_POR_PACIENTE);
		  publicador.publicarMensajeAsnc("miroservicios.odontologia.citaagendada", "miroservicios.odontologia.citaagendada.consultarcita", OdontologiaUtil.serialize(consultaPorPaciente));
	    return new ResponseEntity<List<Cita>>(HttpStatus.OK);
	  }
}
