package microservicios.odontologia.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import microservicios.odontologia.modelo.Cita;

public class Agenda {
	private static final String ID_MEDICO_ESPECIALISTA = "1000";
	private static final String ID_MEDICO_GENERAL = "1001";
	
	private Map<String, List<Cita>> citasPorMedico;
	private Map<String, List<Cita>> citasPorCliente;
	
	public Agenda() {
		citasPorMedico = new HashMap<>();
		citasPorCliente = new HashMap<>();
		
		citasPorMedico.put(ID_MEDICO_ESPECIALISTA, new ArrayList<Cita>());
		citasPorMedico.put(ID_MEDICO_GENERAL, new ArrayList<Cita>());
	}
	
	public void crearCita(Cita cita) {
		
	}
}
