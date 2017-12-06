package microservicios.odontologiaFacturaAPI.odontologiaFacturaAPI.rabbitconf;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;


import java.util.concurrent.CompletableFuture;

@Component
public class Publicador {
	ApplicationContext cxt = new AnnotationConfigApplicationContext(RabbitConfig.class);
	  RabbitTemplate rbt = cxt.getBean(RabbitTemplate.class);

	  public void publicarMensajeSnc(String exchange, String routingK, byte[] mensaje){
	    rbt.convertAndSend(exchange, routingK, mensaje);
	  }

	  public void publicarMensajeAsnc(String exchange, String routingK, byte[] mensaje){
	    CompletableFuture.runAsync(()-> rbt.convertAndSend(exchange, routingK, mensaje));
	  }
}
