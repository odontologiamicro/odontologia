package microservicios.odontologia.util;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import microservicios.odontologia.modelo.Cita;

public class PeticionFacturaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("idPaciente")
	String idPaciente;
	
	@JsonProperty("idMedico")
	String idMedico;
	
	@JsonProperty("fechaCita")
	String fechaCita;

	/**
	 * @return the idPaciente
	 */
	public String getIdPaciente() {
		return idPaciente;
	}

	/**
	 * @param idPaciente the idPaciente to set
	 */
	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}

	/**
	 * @return the idMedico
	 */
	public String getIdMedico() {
		return idMedico;
	}

	/**
	 * @param idMedico the idMedico to set
	 */
	public void setIdMedico(String idMedico) {
		this.idMedico = idMedico;
	}

	/**
	 * @return the fechaCita
	 */
	public String getFechaCita() {
		return fechaCita;
	}

	/**
	 * @param fechaCita the fechaCita to set
	 */
	public void setFechaCita(String fechaCita) {
		this.fechaCita = fechaCita;
	}

	
}
