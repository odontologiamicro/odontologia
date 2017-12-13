package microservicios.odontologiaFacturaAPI.odontologiaFacturaAPI.rabbitconf;

import static microservicios.odontologiaFacturaAPI.odontologiaFacturaAPI.rabbitconf.RabbitConfig.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

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
import microservicios.odontologia.modelo.Paciente;
import microservicios.odontologia.util.OdontologiaUtil;
import microservicios.odontologia.util.PeticionAgendaDTO;
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
	
//	ApplicationContext cxt = new AnnotationConfigApplicationContext(RabbitConfig.class);
//	AsyncRabbitTemplate asyncRabbitTemplate = cxt.getBean(AsyncRabbitTemplate.class);
    
    
//    private Logger log = LoggerFactory.getLogger(Publicador.class);
//
//    public Publicador() {
//    }
//
//    public void send(PeticionFacturaDTO consultaFactura) throws IOException {
//        
//        MessageProperties messageProperties = new MessageProperties();
//        String correlationID = consultaFactura.getIdPaciente() + consultaFactura.getIdMedico() + consultaFactura.getFechaCita() + new Date();
//	    	messageProperties.setCorrelationId(String.valueOf(correlationID).getBytes());
//
//        Message message = new Message(OdontologiaUtil.serialize(consultaFactura),
//					messageProperties);
//		AsyncRabbitTemplate.RabbitConverterFuture<Object> future =
//                asyncRabbitTemplate.convertSendAndReceive(CITA_AGENDADA_EXCHANGE_NAME, QUEUE_FACTURA_CONSULTADA_ROUTING_KEY_NAME, 
//                		message);
//        log.info("Thread Require -->: '{}' messagefor '{}' correlationId '{}'", Thread.currentThread().getName(), consultaFactura.getIdPaciente(), new String(message.getMessageProperties().getCorrelationId()));
//
//        future.addCallback(new ListenableFutureCallback<Object>() {
//            @Override
//            public void onFailure(Throwable throwable) {
//                throwable.printStackTrace();
//            }
//
//            @Override
//            public void onSuccess(Object result) {
//            	try {
//            		OdontologiaUtil.serialize(result);
//            		log.info("RESPONSE REQUIRED --->: '{}' result: '{}' ", Thread.currentThread().getName(), result);
//            	}catch (Exception e) {
//					e.printStackTrace();
//				}
//            }
//        });
//
//    }

}
