package microservicios.odontologiaFactura.odontologiaFactura.rabbitconf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import microservicios.odontologiaFacturaAPI.odontologiaFacturaAPI.modelo.CentroCosto;
import microservicios.odontologiaFacturaAPI.odontologiaFacturaAPI.modelo.Factura;

@Component
public class Consumidor implements MessageListener {
	
	@Autowired
	OdontologiaFacturaCentroCostoIntegracionService integracion;

	public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
	    ByteArrayInputStream in = new ByteArrayInputStream(data);
	    ObjectInputStream is = new ObjectInputStream(in);
	    return is.readObject();
	}

	 @Override
	  public void onMessage(Message message) {
	    try {
	    	String idCentroCosto = "C001";
			System.out.println((Factura)deserialize(message.getBody()));
			System.out.println(new RestTemplate().getForObject("http://localhost:1115//consultaCentroCosto/{idCentroCosto}:",CentroCosto.class, idCentroCosto));
			//System.out.println(integracion.getCentroCostoById("C001"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }

}
