package microservicios.odontologiaFacturaAPI.odontologiaFacturaAPI.rabbitconf;

import static microservicios.odontologiaFacturaAPI.odontologiaFacturaAPI.rabbitconf.RabbitConfig.CITA_AGENDADA_EXCHANGE_NAME;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import microservicios.odontologia.modelo.Factura;
import microservicios.odontologia.util.OdontologiaUtil;
import microservicios.odontologia.util.PeticionFacturaDTO;


@Component
public class Publicador {
	
	ApplicationContext cxt = new AnnotationConfigApplicationContext(RabbitConfig.class);
	 RabbitTemplate rbt = cxt.getBean(RabbitTemplate.class);
   
   public Factura publicarMensajeSnc(String routingK, PeticionFacturaDTO consultaFactura) throws AmqpException, IOException{
	   Factura convertSendAndReceive = (Factura)rbt.convertSendAndReceive(CITA_AGENDADA_EXCHANGE_NAME, routingK, OdontologiaUtil.serialize(consultaFactura));
		return convertSendAndReceive;
   }
   
   public void publicarMensajeAsnc(String routingK, Message mensaje){
   	CompletableFuture.runAsync(()-> rbt.convertAndSend(CITA_AGENDADA_EXCHANGE_NAME, routingK, mensaje));
   }
}
