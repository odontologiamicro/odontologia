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
import microservicios.odontologia.modelo.Factura;
import microservicios.odontologia.modelo.Paciente;
import microservicios.odontologia.util.PeticionAgendaDTO;
import microservicios.odontologia.util.PeticionFacturaDTO;
import microservicios.odontologia.util.OdontologiaUtil;
import microservicios.odontologia.util.TipoConsulta;
import microservicios.odontologiaAppMovilAPI.odontologiaAppMovilAPI.rabbitconfig.Publicador;

@RestController
public class OdontologiaAgendaAPIContoller {
	Publicador publicador = new Publicador();	
	
	  @RequestMapping(method = RequestMethod.POST, value = "/agendarCita")
	  public ResponseEntity<Cita> agendarCita(@RequestBody PeticionAgendaDTO agendaCita) throws IOException{
		  agendaCita.setTipoConsulta(TipoConsulta.AGENDAR_CITA);		  
	    publicador.publicarMensajeAsnc("miroservicios.odontologia.citaagendada", "miroservicios.odontologia.citaagendada.agendar", OdontologiaUtil.serialize(agendaCita));
	    return new ResponseEntity<Cita>(HttpStatus.OK);
	  }
	  
	  @RequestMapping(method = RequestMethod.POST, value = "/consultarCita")
	  public ResponseEntity<List<Cita>> consultarCita(@RequestBody PeticionAgendaDTO consultaPorPaciente) throws IOException{
		  consultaPorPaciente.setTipoConsulta(TipoConsulta.CONSULTAR_CITA_POR_PACIENTE);		  
		  publicador.publicarMensajeAsnc("miroservicios.odontologia.citaagendada", "miroservicios.odontologia.citaagendada.consultarcita", OdontologiaUtil.serialize(consultaPorPaciente));
	    return new ResponseEntity<List<Cita>>(HttpStatus.OK);
	  }
	  
	  //Poner despues de generar cita
//	  @RequestMapping(method = RequestMethod.POST, value = "/facturarCita")
//	  public ResponseEntity<Factura> facturarCita(@RequestBody Cita cita) throws IOException{
//	  PeticionFacturaDTO consultaFactura = new PeticionFacturaDTO();
//	  consultaFactura.setCita(cita);
//	  consultaFactura.setTipoConsulta(TipoConsulta.FA);  
//	  
//	  publicador.publicarMensajeAsnc("miroservicios.odontologia.citaagendada", "miroservicios.odontologia.citaagendada.facturarcita", OdontologiaUtil.serialize(cita));
//	    return new ResponseEntity<Factura>(HttpStatus.OK);
//	  }
}
