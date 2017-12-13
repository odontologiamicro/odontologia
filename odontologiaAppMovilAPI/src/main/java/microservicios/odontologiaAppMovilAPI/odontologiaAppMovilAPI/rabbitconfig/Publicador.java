package microservicios.odontologiaAppMovilAPI.odontologiaAppMovilAPI.rabbitconfig;

import static microservicios.odontologiaAppMovilAPI.odontologiaAppMovilAPI.rabbitconfig.RabbitConfig.EXCHANGE_NAME;

import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import microservicios.odontologia.modelo.Cita;
import microservicios.odontologia.util.OdontologiaUtil;
import microservicios.odontologia.util.PeticionAgendaDTO;
import microservicios.odontologia.util.PeticionFacturaDTO;

@Component
public class Publicador {
	ApplicationContext cxt = new AnnotationConfigApplicationContext(RabbitConfig.class);
	 RabbitTemplate rbt = cxt.getBean(RabbitTemplate.class);
    
    private Logger log = LoggerFactory.getLogger(Publicador.class);
    
    public List<Cita> publicarMensajeSnc(String routingK, PeticionAgendaDTO mensaje) throws AmqpException, IOException{
    	List<Cita> convertSendAndReceive = (List<Cita>)rbt.convertSendAndReceive(EXCHANGE_NAME, routingK, OdontologiaUtil.serialize(mensaje));
		return convertSendAndReceive;
    }
    
    public void publicarMensajeAsnc(String routingK, PeticionFacturaDTO mensaje){
	
		try {
			rbt.convertSendAndReceive(EXCHANGE_NAME, routingK, OdontologiaUtil.serialize(mensaje));
		} catch (AmqpException | IOException e) {
			System.out.println("Error enviando mensaje");
		}
		
    }    

}
