package microservicios.odontologiaFactura.odontologiaFactura.rabbitconf;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import microservicios.odontologia.modelo.CentroCosto;
import microservicios.odontologia.modelo.Cita;
import microservicios.odontologia.modelo.Factura;
import microservicios.odontologia.util.OdontologiaUtil;
import microservicios.odontologia.util.TipoCita;
import microservicios.odontologiaFactura.odontologiaFactura.OdontologiaFacturaCentroCostoIntegracionService;
import microservicios.odontologiaFactura.odontologiaFactura.OdontologiaServicioFacturacion;

@Component
public class Consumidor {
	
	private Logger log = LoggerFactory.getLogger(Consumidor.class);
	
//	@RabbitListener(queues = RabbitConf.QUEUE_FACTURA_CONSULTADA)
    public Factura handleMessage(byte[] request) throws ClassNotFoundException, IOException {
//        log.info("citaConsultada ---> '{}'", new String(request.getMessageProperties().getCorrelationId()));
        String citaBuscada = new String(request);
        Factura factura = OdontologiaServicioFacturacion.getFactura(citaBuscada);
//        MessageProperties messageProperties = new MessageProperties();
//        messageProperties.setCorrelationId(request.getMessageProperties().getCorrelationId());
//    		messageProperties.setCorrelationIdString(request.getMessageProperties().getCorrelationIdString());
//    		Message msj = new Message(OdontologiaUtil.serialize(factura),
//					messageProperties);
        return factura;
    }
	
	
}
