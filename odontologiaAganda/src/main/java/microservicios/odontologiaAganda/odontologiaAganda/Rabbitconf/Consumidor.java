package microservicios.odontologiaAganda.odontologiaAganda.Rabbitconf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
	
	static final String QUEUE_CITA_FACTURADA = "miroservicios.odontologia.facturacion.citafacturada";
	static final String QUEUE_CITA_FACTURADA_ROUTING_KEY_NAME = "miroservicios.odontologia.citaagendada.facturarcita";
	static final String CITA_AGENDADA_EXCHANGE_NAME = "miroservicios.odontologia.citaagendada";

	ApplicationContext cxt = new AnnotationConfigApplicationContext(RabbitConf.class);
	RabbitTemplate rbt = cxt.getBean(RabbitTemplate.class);

    @RabbitListener(queues = {RabbitConf.REQUEST_QUEUE_AGENDAR_NAME, RabbitConf.REQUEST_QUEUE_CONSULTAR_NAME})
    public Message process(@Payload Message request) throws ClassNotFoundException, IOException {
        boolean crearCita = false;
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
			crearCita = true;
			break;
		default:
			break;
		}        
                
        MessageProperties messageProperties = new MessageProperties();
    		messageProperties.setCorrelationId(request.getMessageProperties().getCorrelationId());
    		Message msj = new Message(OdontologiaUtil.serialize(respuesta),
					messageProperties);
    		// enviar mensaje a cola de facturacion
    		
    		if( crearCita && !"".equals( respuesta.get(0).getCodigo() )){
    			publicarMensajeAsnc(CITA_AGENDADA_EXCHANGE_NAME, QUEUE_CITA_FACTURADA_ROUTING_KEY_NAME, OdontologiaUtil.serialize(respuesta.get(0)));
    		}
    		
    		
        return msj;
    }
    
    public void publicarMensajeAsnc(String exchange, String routingK, byte[] mensaje){
    	CompletableFuture.runAsync(()-> rbt.convertAndSend(exchange, routingK, mensaje));
    }
}
