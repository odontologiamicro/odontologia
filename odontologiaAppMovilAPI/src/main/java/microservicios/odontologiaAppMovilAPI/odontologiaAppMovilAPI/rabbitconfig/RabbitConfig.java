package microservicios.odontologiaAppMovilAPI.odontologiaAppMovilAPI.rabbitconfig;

import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
	public static final String EXCHANGE_NAME = "miroservicios.odontologia.citaagendada";
	public static final String REQUEST_QUEUE_CONSULT_NAME = "miroservicios.odontologia.agenda.citaconsultada";
    public static final String REPLY_QUEUE_NAME = "miroservicios.odontologia.agenda.respuestaappmovilReply";
    public static final String ROUTING_KEY_CONSULT_NAME = "miroservicios.odontologia.citaagendada.consultarcita";
    public static final String REQUEST_QUEUE_CREATE_NAME = "miroservicios.odontologia.agenda.citaagendada";
    public static final String ROUTING_KEY_CREATE_NAME = "miroservicios.odontologia.citaagendada.agendar";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Bean
	  public ConnectionFactory connectionFactory(){
		connectionFactory = new CachingConnectionFactory("spider.rmq.cloudamqp.com");
	    connectionFactory.setUsername("durybzqn");
	    connectionFactory.setPassword("QULoTY2fobpvYbOSYBxlt-clMG5nA-V5");
	    connectionFactory.setVirtualHost("durybzqn");
	    connectionFactory.setChannelCheckoutTimeout(10000);
	    connectionFactory.setRequestedHeartBeat(30);
	    return connectionFactory;
	  }

	  @Bean
	  public RabbitTemplate rabbitTemplate(){
		rabbitTemplate = new RabbitTemplate(connectionFactory());
		rabbitTemplate.setReplyTimeout(60000);
		rabbitTemplate.setReceiveTimeout(60000);
	    return rabbitTemplate;
	  }
	  
	  @Bean
	  public AsyncRabbitTemplate template() {
		  SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
	      container.setQueueNames(REPLY_QUEUE_NAME);
	      return new AsyncRabbitTemplate(rabbitTemplate(), container);
	  }	
}
