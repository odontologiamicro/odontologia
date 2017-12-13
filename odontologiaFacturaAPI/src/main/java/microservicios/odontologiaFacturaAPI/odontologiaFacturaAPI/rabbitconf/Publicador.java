package microservicios.odontologiaFacturaAPI.odontologiaFacturaAPI.rabbitconf;

import static microservicios.odontologiaFacturaAPI.odontologiaFacturaAPI.rabbitconf.RabbitConfig.CITA_AGENDADA_EXCHANGE_NAME;
import static microservicios.odontologiaFacturaAPI.odontologiaFacturaAPI.rabbitconf.RabbitConfig.QUEUE_FACTURA_CONSULTADA_ROUTING_KEY_NAME;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import microservicios.odontologia.modelo.Paciente;
import microservicios.odontologia.util.OdontologiaUtil;
import microservicios.odontologia.util.PeticionAgendaDTO;
import microservicios.odontologia.util.PeticionFacturaDTO;


@Component
public class Publicador {
	
	ApplicationContext cxt = new AnnotationConfigApplicationContext(RabbitConfig.class);
	AsyncRabbitTemplate asyncRabbitTemplate = cxt.getBean(AsyncRabbitTemplate.class);
    
    
    private Logger log = LoggerFactory.getLogger(Publicador.class);

    public Publicador() {
    }

    public void send(String codigoCita) throws IOException {
        
        MessageProperties messageProperties = new MessageProperties();
        String correlationID = codigoCita + new Date();
	    	messageProperties.setCorrelationId(String.valueOf(correlationID).getBytes());

        Message message = new Message(codigoCita.getBytes(), messageProperties);
		AsyncRabbitTemplate.RabbitConverterFuture<Object> future =
                asyncRabbitTemplate.convertSendAndReceive(CITA_AGENDADA_EXCHANGE_NAME, QUEUE_FACTURA_CONSULTADA_ROUTING_KEY_NAME, 
                		message);
        log.info("Thread Require -->: '{}' messagefor '{}' correlationId '{}'", Thread.currentThread().getName(), codigoCita, new String(message.getMessageProperties().getCorrelationId()));

        future.addCallback(new ListenableFutureCallback<Object>() {
            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onSuccess(Object result) {
            	try {
            		OdontologiaUtil.serialize(result);
            		log.info("RESPONSE REQUIRED --->: '{}' result: '{}' ", Thread.currentThread().getName(), result);
            	}catch (Exception e) {
					e.printStackTrace();
				}
            }
        });

    }

}
