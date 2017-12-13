package microservicios.odontologia.modelo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Factura implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("codigoCita")
	String codigoCita;
	
	@JsonProperty("valorCita")
	double valorCita;
	
	@JsonProperty("pacienteFactura")
	Paciente paciente;
	
	@JsonProperty("medicoFactura")
	Medico medico;
	
	@JsonProperty("tipoCitaFactura")
	int tipoCita;
	
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

	public double getValorCita() {
		return valorCita;
	}

	public void setValorCita(double valorCita) {
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

	public int getTipoCita() {
		return tipoCita;
	}

	public void setTipoCita(int tipoCita) {
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
