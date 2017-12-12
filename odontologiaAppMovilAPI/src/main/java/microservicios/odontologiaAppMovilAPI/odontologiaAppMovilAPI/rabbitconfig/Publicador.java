package microservicios.odontologiaAppMovilAPI.odontologiaAppMovilAPI.rabbitconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import microservicios.odontologia.modelo.Cita;
import microservicios.odontologia.util.OdontologiaUtil;
import microservicios.odontologia.util.PeticionAgendaDTO;
import static microservicios.odontologiaAppMovilAPI.odontologiaAppMovilAPI.rabbitconfig.RabbitConfig.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class Publicador {
	ApplicationContext cxt = new AnnotationConfigApplicationContext(RabbitConfig.class);
	AsyncRabbitTemplate asyncRabbitTemplate = cxt.getBean(AsyncRabbitTemplate.class);
    
    
    private Logger log = LoggerFactory.getLogger(Publicador.class);

    public Publicador() {
    }

    public void send(String rKey, PeticionAgendaDTO pAgendaDTO) throws IOException {
    	UUID correlationId = UUID.randomUUID();
    	
        MessageProperties messageProperties = new MessageProperties();
	    	messageProperties.setCorrelationId(correlationId.toString().getBytes());

        AsyncRabbitTemplate.RabbitConverterFuture<Object> future =
                asyncRabbitTemplate.convertSendAndReceive(EXCHANGE_NAME, rKey, 
                		new Message(OdontologiaUtil.serialize(pAgendaDTO),
    							messageProperties));

        future.addCallback(new ListenableFutureCallback<Object>() {
            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onSuccess(Object result) {
//            	try {
//					List<Cita> citas = (List<Cita>)OdontologiaUtil.deserialize(((Message)result).getBody());
//					for (Cita cita: citas) {
//						System.out.println("Cita ==> " + cita);
//					}
//				} catch (ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
                log.info("Respuesta recibida...");
            }
        });

    }
}
