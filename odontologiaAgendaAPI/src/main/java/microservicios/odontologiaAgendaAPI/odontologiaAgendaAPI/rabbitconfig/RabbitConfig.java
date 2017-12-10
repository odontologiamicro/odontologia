package microservicios.odontologiaAgendaAPI.odontologiaAgendaAPI.rabbitconfig;

import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
	static final String FIBO_CALCULATOR_EXCHANGE_NAME = "miroservicios.odontologia.citaagendada";
    static final String FIBO_CALCULATOR_REQUEST_QUEUE_NAME = "miroservicios.odontologia.agenda.citaconsultada";
    static final String FIBO_CALCULATOR_REPLY_QUEUE_NAME = "miroservicios.odontologia.agenda.citaconsultadaReply";
    static final String FIBO_CALCULATOR_ROUTING_KEY_NAME = "miroservicios.odontologia.citaagendada.consultarcita";

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
	    return rabbitTemplate;
	  }
	  
	    @Bean
	    public AsyncRabbitTemplate template() {
	        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
	        container.setQueueNames(FIBO_CALCULATOR_REPLY_QUEUE_NAME);
	        return new AsyncRabbitTemplate(rabbitTemplate(), container);
	    }


}
