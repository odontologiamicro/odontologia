package microservicios.odontologiaFactura.odontologiaFactura.rabbitconf;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.netflix.discovery.converters.Auto;

import microservicios.odontologia.modelo.CentroCosto;
import microservicios.odontologia.modelo.Cita;
import microservicios.odontologia.modelo.Factura;
import microservicios.odontologia.util.OdontologiaUtil;
import microservicios.odontologia.util.PeticionAgendaDTO;
import microservicios.odontologia.util.PeticionFacturaDTO;
import microservicios.odontologia.util.TipoCita;
import microservicios.odontologiaFactura.odontologiaFactura.OdontologiaFacturaCentroCostoIntegracionService;
import microservicios.odontologiaFactura.odontologiaFactura.OdontologiaServicioFacturacion;

@Component
public class Consumidor {
	
	@Autowired
	OdontologiaFacturaCentroCostoIntegracionService service;
	
	private Logger log = LoggerFactory.getLogger(Consumidor.class);
	
	@RabbitListener(queues = RabbitConf.QUEUE_FACTURA_CONSULTADA)
    public Message consultarFactura(@Payload Message request) throws ClassNotFoundException, IOException {
        log.info("citaConsultada ---> '{}'", new String(request.getMessageProperties().getCorrelationId()));
        PeticionFacturaDTO dto = (PeticionFacturaDTO)OdontologiaUtil.deserialize(request.getBody());
        String citaBuscada = dto.getIdPaciente() + "-" +dto.getIdMedico() + "-" + dto.getFechaCita();
        Factura factura = OdontologiaServicioFacturacion.getFactura(citaBuscada);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setCorrelationId(request.getMessageProperties().getCorrelationId());
    		messageProperties.setCorrelationIdString(request.getMessageProperties().getCorrelationIdString());
    		Message msj = new Message(OdontologiaUtil.serialize(factura),
					messageProperties);
        return msj;
    }
	
	@RabbitListener(queues = RabbitConf.QUEUE_CITA_FACTURADA)
    public void facturarCita(@Payload Message request) throws ClassNotFoundException, IOException {
		Cita cita = (Cita) OdontologiaUtil.deserialize(request.getBody());
		CentroCosto centro;
		double valorCita;
		switch (cita.getTipoCita()) {
		case TipoCita.CITA_CON_ESPECIALISTA:
			centro = service.getCentroCostoById("C001");
			valorCita = 30000;
			break;

		default:
			centro = service.getCentroCostoById("C002");
			valorCita = 10000;
			break;
		}
		Factura factura = new Factura();
		factura.setCentroCosto(centro);
		factura.setCodigoCita(cita.getCodigo());
		factura.setFecha(cita.getFecha());
		factura.setMedico(cita.getMedico());
		factura.setPaciente(cita.getPaciente());
		factura.setTipoCita(cita.getTipoCita());
		factura.setValorCita(valorCita);
		
		String claveFactura = cita.getPaciente().getId() + "-" +cita.getMedico().getId()+ "-" + cita.getFecha();
		OdontologiaServicioFacturacion.addFactura(claveFactura, factura);
        log.info("citaFacturada ---> '{}'", request);
    }

}
