package microservicios.odontologiaFactura.odontologiaFactura.rabbitconf;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
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

}
