package microservicios.odontologiaAgendaAPI.odontologiaAgendaAPI;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.metric.consumer.BucketedCounterStream;

import microservicios.odontologia.modelo.Cita;
import microservicios.odontologia.modelo.Medico;
import microservicios.odontologia.util.OdontologiaUtil;
import microservicios.odontologiaAgendaAPI.odontologiaAgendaAPI.rabbitconfig.Publicador;

@RestController
public class OdontologiaAgendaAPIContoller {
	Publicador publicador = new Publicador();	
	
	  @RequestMapping(method = RequestMethod.POST, value = "/cita")
	  public ResponseEntity<Medico> consultarCita(@RequestBody Medico medico) throws IOException{
	    publicador.publicarMensajeAsnc("miroservicios.odontologia.citaagendada", "miroservicios.odontologia.citaagendada.consultarcita", OdontologiaUtil.serialize(medico));
	    return new ResponseEntity<Medico>(HttpStatus.OK);
	  }
}
