package microservicios.odontologiaAgendaAPI.odontologiaAgendaAPI.rabbitconfig;

import static microservicios.odontologiaAgendaAPI.odontologiaAgendaAPI.rabbitconfig.RabbitConfig.FIBO_CALCULATOR_EXCHANGE_NAME;
import static microservicios.odontologiaAgendaAPI.odontologiaAgendaAPI.rabbitconfig.RabbitConfig.FIBO_CALCULATOR_ROUTING_KEY_NAME;

import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import microservicios.odontologia.modelo.Paciente;
import microservicios.odontologia.util.PeticionAgendaDTO;

@Component
public class Publicador {

	ApplicationContext cxt = new AnnotationConfigApplicationContext(RabbitConfig.class);
	AsyncRabbitTemplate asyncRabbitTemplate = cxt.getBean(AsyncRabbitTemplate.class);
    
    
    private Logger log = LoggerFactory.getLogger(Publicador.class);

    public Publicador() {
    }

    public void send() {
        int number = new Random().nextInt(45);
        PeticionAgendaDTO pAgendaDTO = new PeticionAgendaDTO();
        pAgendaDTO.setTipoConsulta(2);
        Paciente p = new Paciente();
        p.setId(number);
        pAgendaDTO.setPaciente(p);
        
        MessageProperties messageProperties = new MessageProperties();
	    	messageProperties.setCorrelationIdString(String.valueOf(pAgendaDTO.getPaciente().getId()) + new Date() );

        AsyncRabbitTemplate.RabbitConverterFuture<String> future =
                asyncRabbitTemplate.convertSendAndReceive(FIBO_CALCULATOR_EXCHANGE_NAME, FIBO_CALCULATOR_ROUTING_KEY_NAME, 
                		new Message(pAgendaDTO.toString().getBytes(),
    							messageProperties));
        log.info("Thread: '{}' calc fibonacci for number '{}'", Thread.currentThread().getName(), pAgendaDTO.getPaciente().getId());

        future.addCallback(new ListenableFutureCallback<String>() {
            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onSuccess(String result) {
                log.info("Thread: '{}' result: '{}'", Thread.currentThread().getName(), result);
            }
        });

    }
    
//	@Autowired
//	private AsyncRabbitTemplate asyncRabbitTemplate;
//    
//    private Logger log = LoggerFactory.getLogger(Publicador.class);
//
//    public Publicador() {
//    }
//    
//    public void send(String exchange, String routingK, PeticionAgendaDTO mensaje) {
//  
//
//        AsyncRabbitTemplate.RabbitConverterFuture<PeticionAgendaDTO> future =
//                asyncRabbitTemplate.convertSendAndReceive(exchange, routingK, mensaje);
//        log.info("Thread: '{}' calc fibonacci for number '{}'", Thread.currentThread().getName(), mensaje);
//
//        future.addCallback(new ListenableFutureCallback<PeticionAgendaDTO>() {
//            @Override
//            public void onFailure(Throwable throwable) {
//                throwable.printStackTrace();
//            }
//
//            @Override
//            public void onSuccess(PeticionAgendaDTO result) {
//                log.info("Thread: '{}' result: '{}'", Thread.currentThread().getName(), result);
//            }
//        });
//
//}
}
