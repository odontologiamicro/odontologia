package microservicios.odontologiaFactura.odontologiaFactura.rabbitconf;

import java.io.IOException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import microservicios.odontologia.modelo.Factura;
import microservicios.odontologia.util.OdontologiaUtil;
import microservicios.odontologia.util.PeticionFacturaDTO;
import microservicios.odontologia.util.TipoConsulta;

@Component
public class Consumidor implements MessageListener {
		
	 @Override
	  public void onMessage(Message message) {
	    try {
	    	PeticionFacturaDTO peticionFacturaDTO = (PeticionFacturaDTO)OdontologiaUtil.deserialize(message.getBody());
	    	switch (peticionFacturaDTO.getTipoConsulta()) {
			case TipoConsulta.CONSULTAR_FACTURA:
				System.out.println("Consultando factura cita: " + peticionFacturaDTO.getCita());
				break;
			case TipoConsulta.FACTURAR_CITA:
				System.out.println("facturando cita: " + peticionFacturaDTO.getCita());
				break;	
			default:
				break;
			}
	    				
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
}
