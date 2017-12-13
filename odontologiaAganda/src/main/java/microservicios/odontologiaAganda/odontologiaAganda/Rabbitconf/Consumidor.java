package microservicios.odontologiaAganda.odontologiaAganda.Rabbitconf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import microservicios.odontologia.modelo.Agenda;
import microservicios.odontologia.modelo.Cita;
import microservicios.odontologia.util.OdontologiaUtil;
import microservicios.odontologia.util.PeticionAgendaDTO;
import microservicios.odontologia.util.TipoConsulta;

@Component
public class Consumidor {

private Logger log = LoggerFactory.getLogger(Consumidor.class);
	
	static final String QUEUE_CITA_FACTURADA = "miroservicios.odontologia.facturacion.citafacturada";
	static final String QUEUE_CITA_FACTURADA_ROUTING_KEY_NAME = "miroservicios.odontologia.citaagendada.facturarcita";
	static final String CITA_AGENDADA_EXCHANGE_NAME = "miroservicios.odontologia.citaagendada";
    
    public List<Cita> handleMessage(byte[] peticion) throws ClassNotFoundException, IOException {
    	boolean crearCita = false;
    	PeticionAgendaDTO peticionAgendaDTO = (PeticionAgendaDTO)OdontologiaUtil.deserialize(peticion);
    	List<Cita> respuesta = new ArrayList<>();
	    Agenda.inicializar();
        switch (peticionAgendaDTO.getTipoConsulta()) {
		case TipoConsulta.CONSULTAR_CITA_POR_MEDICO:
			log.info("Consultando por medico" + peticionAgendaDTO.getMedico());
			respuesta = Agenda.consultarPorMedico(peticionAgendaDTO.getMedico());
			break;
		case TipoConsulta.CONSULTAR_CITA_POR_PACIENTE:
			log.info("Consultando por paciente" + peticionAgendaDTO.getPaciente());
			respuesta = Agenda.consultarPorPaciente(peticionAgendaDTO.getPaciente());
			break;
		case TipoConsulta.AGENDAR_CITA:
			log.info("Agendadondo cita" + peticionAgendaDTO.getCita());
			respuesta.add(Agenda.crearCita(peticionAgendaDTO.getCita()));
			crearCita = true;
			break;
		default:
			break;
		}        
        
		return respuesta;
	}
    
}
