package microservicios.odontologiaAganda.odontologiaAganda.Rabbitconf;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConf {
	
	static final String REQUEST_QUEUE_AGENDAR_NAME = "miroservicios.odontologia.agenda.citaagendada";
	static final String REQUEST_QUEUE_CONSULTAR_NAME = "miroservicios.odontologia.agenda.citaconsultada";
	
	@Bean
	  public ConnectionFactory connectionFactory(){
	    CachingConnectionFactory connectionFactory = new CachingConnectionFactory("spider.rmq.cloudamqp.com");
	    connectionFactory.setUsername("durybzqn");
	    connectionFactory.setPassword("QULoTY2fobpvYbOSYBxlt-clMG5nA-V5");
	    connectionFactory.setVirtualHost("durybzqn");
	    connectionFactory.setChannelCheckoutTimeout(10000);
	    connectionFactory.setRequestedHeartBeat(30);
	    return connectionFactory;
	  }

}
