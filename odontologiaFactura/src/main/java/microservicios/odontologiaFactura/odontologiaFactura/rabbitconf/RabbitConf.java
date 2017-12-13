package microservicios.odontologiaFactura.odontologiaFactura.rabbitconf;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConf {
	
	static final String QUEUE_FACTURA_CONSULTADA= "miroservicios.odontologia.facturacion.facturaconsultada";
	static final String QUEUE_CITA_FACTURADA = "miroservicios.odontologia.facturacion.citafacturada";
	
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
	
	@Bean
	public SimpleMessageListenerContainer serviceListenerContainer() {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory( connectionFactory() );
		container.setQueues(new Queue(QUEUE_FACTURA_CONSULTADA) );
		container.setMessageListener(new MessageListenerAdapter(new Consumidor()));
		return container;
	}
	
	@Bean
	public SimpleMessageListenerContainer serviceListenerContainerDos() {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory( connectionFactory() );
		container.setQueues( new Queue(QUEUE_CITA_FACTURADA) );
		container.setMessageListener(new MessageListenerAdapter(new ConsumidorDos()));
		return container;
	}

}
