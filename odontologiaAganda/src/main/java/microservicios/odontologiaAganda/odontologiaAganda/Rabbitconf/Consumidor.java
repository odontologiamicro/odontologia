package microservicios.odontologiaAganda.odontologiaAganda.Rabbitconf;

import java.io.IOException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import microservicios.odontologia.modelo.Cita;
import microservicios.odontologia.util.OdontologiaUtil;
import microservicios.odontologia.util.PeticionAgendaDTO;
import microservicios.odontologia.util.TipoConsulta;

@Component
public class Consumidor implements MessageListener {
		
	 @Override
	  public void onMessage(Message message) {
	    try {
	    	PeticionAgendaDTO peticionAgendaDTO = (PeticionAgendaDTO)OdontologiaUtil.deserialize(message.getBody());
	    	
	    	switch (peticionAgendaDTO.getTipoConsulta()) {
			case TipoConsulta.CONSULTAR_CITA_POR_MEDICO:
				System.out.println("Consultando por medico" + peticionAgendaDTO.getMedico());
				break;
			case TipoConsulta.CONSULTAR_CITA_POR_PACIENTE:
				System.out.println("Consultando por paciente" + peticionAgendaDTO.getPaciente());
				break;
			case TipoConsulta.AGENDAR_CITA:
				System.out.println("Agendadondo cita" + peticionAgendaDTO.getCita());
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
