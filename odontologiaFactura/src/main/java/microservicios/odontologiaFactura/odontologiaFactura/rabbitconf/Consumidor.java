package microservicios.odontologiaFactura.odontologiaFactura.rabbitconf;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import microservicios.odontologia.modelo.CentroCosto;
import microservicios.odontologia.modelo.Cita;
import microservicios.odontologia.modelo.Factura;
import microservicios.odontologia.util.OdontologiaUtil;
import microservicios.odontologia.util.PeticionFacturaDTO;
import microservicios.odontologia.util.TipoCita;
import microservicios.odontologia.util.TipoConsulta;
import microservicios.odontologiaFactura.odontologiaFactura.OdontologiaFacturaCentroCostoIntegracionService;
import microservicios.odontologiaFactura.odontologiaFactura.OdontologiaServicioFacturacion;

@Component
public class Consumidor implements ApplicationContextAware {
	
	private Logger log = LoggerFactory.getLogger(Consumidor.class);
	private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;   
    }

    public static ApplicationContext getContext() {
        return context;
    }


    public Factura handleMessage(byte[] request) {
//        log.info("citaConsultada ---> '{}'", new String(request.getMessageProperties().getCorrelationId()));
        
    	PeticionFacturaDTO dtoFactura = new PeticionFacturaDTO();
		try {
			dtoFactura = (PeticionFacturaDTO) OdontologiaUtil.deserialize(request);
		} catch (ClassNotFoundException | IOException e) {
			log.info("Error al deserializar mensaje");
		}
    	Factura factura = new Factura();
    	switch(dtoFactura.getTipoPeticion()){
    		case TipoConsulta.CONSULTAR_FACTURA:
    			 factura = OdontologiaServicioFacturacion.getFactura(dtoFactura.getCita().getCodigo());
    			break;
    		case TipoConsulta.FACTURAR_CITA:
    			crearFactura(dtoFactura.getCita());
    			break;
    		default:
    			break;
    	}
    	
       
        return factura;
    }

	private void crearFactura(Cita cita) {
		OdontologiaFacturaCentroCostoIntegracionService service = this.getContext().getBean(OdontologiaFacturaCentroCostoIntegracionService.class);
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
		log.info("citaFacturada ---> '{}'", cita.getCodigo());
	}
	
	
}
