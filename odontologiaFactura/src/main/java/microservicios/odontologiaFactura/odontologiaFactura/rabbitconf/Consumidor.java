package microservicios.odontologiaFactura.odontologiaFactura.rabbitconf;

import java.io.IOException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import microservicios.odontologia.modelo.Factura;
import microservicios.odontologia.util.OdontologiaUtil;

@Component
public class Consumidor implements MessageListener {
		
	 @Override
	  public void onMessage(Message message) {
	    try {
			System.out.println((Factura)OdontologiaUtil.deserialize(message.getBody()));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
}
