package microservicios.odontologiaAganda.odontologiaAganda.Rabbitconf;

import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import microservicios.odontologia.modelo.Cita;
import microservicios.odontologia.util.OdontologiaUtil;
import microservicios.odontologia.util.PeticionAgendaDTO;
import microservicios.odontologia.util.TipoConsulta;

@Component
public class Consumidor {

private Logger log = LoggerFactory.getLogger(Consumidor.class);
	
    @RabbitListener(queues = RabbitConf.FIBO_CALCULATOR_REQUEST_QUEUE_NAME)
    public Message process(@Payload Message request) {
        log.info("Received '{}'", request);
        PeticionAgendaDTO dto = new PeticionAgendaDTO();
        dto.setCita(new Cita());
        MessageProperties messageProperties = new MessageProperties();
    		messageProperties.setCorrelationIdString(request.getMessageProperties().getCorrelationIdString());
    		Message msj = new Message(dto.toString().getBytes(),
					messageProperties);
    		// enviar mensaje a cola de facturacion
        return msj;
    }
    
	  public PeticionAgendaDTO getCita(PeticionAgendaDTO request) {
		  PeticionAgendaDTO peticionAgendaDTO = request;
	    switch (peticionAgendaDTO.getTipoConsulta()) {
		case TipoConsulta.CONSULTAR_CITA_POR_MEDICO:
			System.out.println("Consultando por medico" + peticionAgendaDTO.getMedico());
			break;
		case TipoConsulta.CONSULTAR_CITA_POR_PACIENTE:
			System.out.println("Consultando por paciente" + peticionAgendaDTO.getPaciente());
			break;
		case TipoConsulta.AGENDAR_CITA:
			System.out.println("Agendadondo cita" + peticionAgendaDTO.getCita());
			break;
		default:
			break;
		}
	    
	    return peticionAgendaDTO;
	  }
}
