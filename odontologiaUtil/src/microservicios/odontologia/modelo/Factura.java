package microservicios.odontologia.modelo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import microservicios.odontologia.util.TipoCita;

public class Factura implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("codigoCita")
	String codigoCita;
	
	@JsonProperty("valorCita")
	String valorCita;
	
	@JsonProperty("pacienteFactura")
	Paciente paciente;
	
	@JsonProperty("medicoFactura")
	Medico medico;
	
	@JsonProperty("tipoCitaFactura")
	TipoCita tipoCita;
	
	@JsonProperty("centroCosto")
	CentroCosto centroCosto;
	
	@JsonProperty("fechaFactura")
	String fecha; //AAAAMMDD

	public String getCodigoCita() {
		return codigoCita;
	}

	public void setCodigoCita(String codigoCita) {
		this.codigoCita = codigoCita;
	}

	public String getValorCita() {
		return valorCita;
	}

	public void setValorCita(String valorCita) {
		this.valorCita = valorCita;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public TipoCita getTipoCita() {
		return tipoCita;
	}

	public void setTipoCita(TipoCita tipoCita) {
		this.tipoCita = tipoCita;
	}

	public CentroCosto getCentroCosto() {
		return centroCosto;
	}

	public void setCentroCosto(CentroCosto centroCosto) {
		this.centroCosto = centroCosto;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}	
}
