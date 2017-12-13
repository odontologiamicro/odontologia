package microservicios.odontologiaAppMovilAPI.odontologiaAppMovilAPI.rabbitconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import microservicios.odontologia.modelo.Cita;
import microservicios.odontologia.modelo.Factura;
import microservicios.odontologia.util.OdontologiaUtil;
import microservicios.odontologia.util.PeticionAgendaDTO;
import microservicios.odontologia.util.PeticionFacturaDTO;

import static microservicios.odontologiaAppMovilAPI.odontologiaAppMovilAPI.rabbitconfig.RabbitConfig.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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
    
//    public void send(String rKey, PeticionAgendaDTO pAgendaDTO) throws IOException {
//    	UUID correlationId = UUID.randomUUID();
//    	
//        MessageProperties messageProperties = new MessageProperties();
//	    	messageProperties.setCorrelationId(correlationId.toString().getBytes());
//
//        AsyncRabbitTemplate.RabbitConverterFuture<Object> future =
//                asyncRabbitTemplate.convertSendAndReceive(EXCHANGE_NAME, rKey, 
//                		new Message(OdontologiaUtil.serialize(pAgendaDTO),
//    							messageProperties));
//
//        future.addCallback(new ListenableFutureCallback<Object>() {
//            @Override
//            public void onFailure(Throwable throwable) {
//                throwable.printStackTrace();
//            }
//
//            @Override
//            public void onSuccess(Object result) {
////            	try {
////					List<Cita> citas = (List<Cita>)OdontologiaUtil.deserialize(((Message)result).getBody());
////					for (Cita cita: citas) {
////						System.out.println("Cita ==> " + cita);
////					}
////				} catch (ClassNotFoundException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				} catch (IOException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
//                log.info("Respuesta recibida...");
//            }
//        });
//
//    }
}
