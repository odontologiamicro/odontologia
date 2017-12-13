package microservicios.odontologiaFactura.odontologiaFactura.rabbitconf;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
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
public class ConsumidorDos {

	@Autowired
	OdontologiaFacturaCentroCostoIntegracionService service;
	
	private Logger log = LoggerFactory.getLogger(ConsumidorDos.class);
	
//	@RabbitListener(queues = RabbitConf.QUEUE_CITA_FACTURADA)
    public void handleMessage(byte[] request) throws ClassNotFoundException, IOException {
		Cita cita = (Cita) OdontologiaUtil.deserialize(request);
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
		
		String claveFactura = cita.getCodigo();
		OdontologiaServicioFacturacion.addFactura(claveFactura, factura);
        log.info("citaFacturada ---> '{}'", request);
    }

	
	
}
