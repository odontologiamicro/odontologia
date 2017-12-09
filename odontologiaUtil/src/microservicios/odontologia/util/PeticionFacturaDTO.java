package microservicios.odontologia.util;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import microservicios.odontologia.modelo.Cita;

public class PeticionFacturaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("citaFactura")
	Cita cita;
	
	@JsonProperty("tipoConsultaFactura")
	int tipoConsulta;

	public Cita getCita() {
		return cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}

	public int getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(int tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}	
}
