package microservicios.odontologiaFactura.odontologiaFactura.rabbitconf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import microservicios.odontologiaFacturaAPI.odontologiaFacturaAPI.modelo.Factura;

@Component
public class Consumidor implements MessageListener {
	public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
	    ByteArrayInputStream in = new ByteArrayInputStream(data);
	    ObjectInputStream is = new ObjectInputStream(in);
	    return is.readObject();
	}
	
	 @Override
	  public void onMessage(Message message) {
	    try {
			System.out.println((Factura)deserialize(message.getBody()));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
}
