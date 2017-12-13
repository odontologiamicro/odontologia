package microservicios.odontologiaAppMovilAPI.odontologiaAppMovilAPI;

import java.io.IOException;
import java.util.List;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import microservicios.odontologia.modelo.Cita;
import microservicios.odontologia.util.OdontologiaUtil;
import microservicios.odontologia.util.PeticionAgendaDTO;
import microservicios.odontologia.util.PeticionFacturaDTO;
import microservicios.odontologia.util.TipoConsulta;
import microservicios.odontologiaAppMovilAPI.odontologiaAppMovilAPI.rabbitconfig.Publicador;
import static microservicios.odontologiaAppMovilAPI.odontologiaAppMovilAPI.rabbitconfig.RabbitConfig.*;

@RestController
public class OdontologiaAgendaAPIContoller {
	Publicador publicador = new Publicador();
	static final String QUEUE_CITA_FACTURADA_ROUTING_KEY_NAME = "miroservicios.odontologia.citaagendada.facturarcita";
	
	  @RequestMapping(method = RequestMethod.POST, value = "/agendarCita")
	  public ResponseEntity<Cita> agendarCita(@RequestBody PeticionAgendaDTO agendaCita) throws IOException{
		  agendaCita.setTipoConsulta(TipoConsulta.AGENDAR_CITA);
		  List<Cita> lstCita = publicador.publicarMensajeSnc(ROUTING_KEY_CREATE_NAME, agendaCita);
		  
		  PeticionFacturaDTO dto = new PeticionFacturaDTO();
		  dto.setCita(lstCita.get(0));
		  dto.setTipoPeticion(TipoConsulta.FACTURAR_CITA);
	        
	        if( !"".equals( lstCita.get(0).getCodigo() )){
	        	publicador.publicarMensajeAsnc(QUEUE_CITA_FACTURADA_ROUTING_KEY_NAME, dto);
			}
		  return new ResponseEntity<Cita>(lstCita.get(0),  HttpStatus.OK);
	  }
	  
	  @RequestMapping(method = RequestMethod.POST, value = "/consultarCita")
	  public ResponseEntity<List<Cita>> consultarCita(@RequestBody PeticionAgendaDTO consultaPorPaciente) throws IOException{
		  consultaPorPaciente.setTipoConsulta(TipoConsulta.CONSULTAR_CITA_POR_PACIENTE);		  
		  List<Cita> lstCita = publicador.publicarMensajeSnc(ROUTING_KEY_CONSULT_NAME, consultaPorPaciente);
	    return new ResponseEntity<List<Cita>>(lstCita, HttpStatus.OK);
	  }	  
}
