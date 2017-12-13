package microservicios.odontologia.util;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import microservicios.odontologia.modelo.Cita;


public class PeticionFacturaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("citaFacturada")
	private Cita cita;

	@JsonProperty("tipoPeticion")
	private int tipoPeticion;

	public int getTipoPeticion() {
		return tipoPeticion;
	}

	public void setTipoPeticion(int tipoPeticion) {
		this.tipoPeticion = tipoPeticion;
	}

	public Cita getCita() {
		return cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}

}
