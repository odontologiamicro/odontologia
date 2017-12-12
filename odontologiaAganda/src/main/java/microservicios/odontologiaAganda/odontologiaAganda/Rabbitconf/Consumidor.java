package microservicios.odontologiaAganda.odontologiaAganda.Rabbitconf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import microservicios.odontologia.modelo.Agenda;
import microservicios.odontologia.modelo.Cita;
import microservicios.odontologia.util.OdontologiaUtil;
import microservicios.odontologia.util.PeticionAgendaDTO;
import microservicios.odontologia.util.TipoConsulta;

@Component
public class Consumidor {

private Logger log = LoggerFactory.getLogger(Consumidor.class);
	
    @RabbitListener(queues = {RabbitConf.REQUEST_QUEUE_AGENDAR_NAME, RabbitConf.REQUEST_QUEUE_CONSULTAR_NAME})
    public Message process(@Payload Message request) throws ClassNotFoundException, IOException {
        
        PeticionAgendaDTO peticionAgendaDTO = (PeticionAgendaDTO)OdontologiaUtil.deserialize(request.getBody());
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
			break;
		default:
			break;
		}        
                
        MessageProperties messageProperties = new MessageProperties();
    		messageProperties.setCorrelationId(request.getMessageProperties().getCorrelationId());
    		Message msj = new Message(OdontologiaUtil.serialize(respuesta),
					messageProperties);
    		// enviar mensaje a cola de facturacion
        return msj;
    }	  
}
