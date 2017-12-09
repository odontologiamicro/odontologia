package microservicios.odontologia.util;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import microservicios.odontologia.modelo.Cita;
import microservicios.odontologia.modelo.Medico;
import microservicios.odontologia.modelo.Paciente;

public class PeticionAgendaDTO  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("citaConsulta")
	private Cita cita;
	
	@JsonProperty("medicoConsulta")
	private Medico medico;
	
	@JsonProperty("pacienteConsulta")
	private Paciente paciente;
	
	@JsonProperty("tipoConsultaAgenda")
	private int tipoConsulta;
	
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public int getTipoConsulta() {
		return tipoConsulta;
	}
	public void setTipoConsulta(int tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
	public Cita getCita() {
		return cita;
	}
	public void setCita(Cita cita) {
		this.cita = cita;
	}	
}
