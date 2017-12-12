package microservicios.odontologiaFacturaAPI.odontologiaFacturaAPI.rabbitconf;

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

	static final String CITA_AGENDADA_EXCHANGE_NAME = "miroservicios.odontologia.citaagendada";
	static final String QUEUE_CITA_FACTURADA = "miroservicios.odontologia.facturacion.citafacturada";
	static final String QUEUE_CITA_FACTURADA_ROUTING_KEY_NAME = "miroservicios.odontologia.citaagendada.facturarcita";
	static final String QUEUE_CITA_FACTURA_CONSULTADA = "miroservicios.odontologia.facturacion.facturaconsultada";
	static final String QUEUE_FACTURA_CONSULTADA_ROUTING_KEY_NAME ="miroservicios.odontologia.citaagendada.consultarfactura";
	static final String QUEUE_FACTURA_CONSULTADA_REPLY = "miroservicios.odontologia.facturacion.facturaconsultadaReply";

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private CachingConnectionFactory connectionFactory;

	@Bean
	public ConnectionFactory connectionFactory() {
		connectionFactory = new CachingConnectionFactory("spider.rmq.cloudamqp.com");
		connectionFactory.setUsername("durybzqn");
		connectionFactory.setPassword("QULoTY2fobpvYbOSYBxlt-clMG5nA-V5");
		connectionFactory.setVirtualHost("durybzqn");
		connectionFactory.setChannelCheckoutTimeout(10000);
		connectionFactory.setRequestedHeartBeat(30);
		return connectionFactory;
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		rabbitTemplate = new RabbitTemplate(connectionFactory());
		return rabbitTemplate;
	}

	@Bean
	public AsyncRabbitTemplate template() {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
		container.setQueueNames(QUEUE_FACTURA_CONSULTADA_REPLY);
		return new AsyncRabbitTemplate(rabbitTemplate(), container);
	}

}
