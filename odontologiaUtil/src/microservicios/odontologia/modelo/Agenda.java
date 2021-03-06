package microservicios.odontologia.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import microservicios.odontologia.modelo.Cita;

public class Agenda {
	private static final long ID_MEDICO_ESPECIALISTA = 1000;
	private static final long ID_MEDICO_GENERAL = 1001;
	
	private static ConcurrentMap<Long, List<Cita>> citasPorMedico;
	private static ConcurrentMap<Long, List<Cita>> citasPorPaciente;
	
	public static void inicializar() {
		
		if( citasPorMedico == null ){
			citasPorMedico = new ConcurrentHashMap<>();
			citasPorMedico.put(ID_MEDICO_ESPECIALISTA, new ArrayList<Cita>());
			citasPorMedico.put(ID_MEDICO_GENERAL, new ArrayList<Cita>());
		}
		
		if( citasPorPaciente ==  null ){
			citasPorPaciente = new ConcurrentHashMap<>();
		}
	}
	
	public static Cita crearCita(Cita cita) {
		if( cita != null && cita.getMedico() != null && cita.getPaciente() != null ){
			if (ID_MEDICO_ESPECIALISTA == cita.getMedico().getId() ||
					ID_MEDICO_GENERAL == cita.getMedico().getId()) {
				boolean citaDisponible = true;
				List<Cita> citasDelMedico = citasPorMedico.get(cita.getMedico().getId());
				if (!citasPorPaciente.containsKey(cita.getPaciente().getId())) {
					citasPorPaciente.put(cita.getPaciente().getId(), new ArrayList<Cita>());
				}
				List<Cita> citasDelPaciente = citasPorPaciente.get(cita.getPaciente().getId());
				
				for (Cita c: citasDelMedico) {
					if (c.getHora() == cita.getHora() && c.getFecha().equals(cita.getFecha())) {
						citaDisponible = false;
						break;
					}
				}
				
				if (citaDisponible) {
					for (Cita c: citasDelPaciente) {
						if (c.getHora() == cita.getHora() && c.getFecha().equals(cita.getFecha())) {
							citaDisponible = false;
							break;
						}
					}
				}
				
				if (citaDisponible) {
					UUID codigoCita = UUID.randomUUID();
					cita.setCodigo(codigoCita.toString());
					citasDelMedico.add(cita);
					citasDelPaciente.add(cita);
					return cita;
				}
			}	
		}
		
		return new Cita();
	}
	
	public static List<Cita> consultarPorMedico(Medico medico) {
		if( medico != null )
			return citasPorMedico.get(medico.getId());	
		return new ArrayList<>();
	}
	
	public static List<Cita> consultarPorPaciente(Paciente paciente) {
		if( paciente != null )
			return citasPorPaciente.get(paciente.getId());
		return new ArrayList<>();
	}
}
