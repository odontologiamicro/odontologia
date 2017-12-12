package microservicios.odontologia.modelo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import microservicios.odontologia.modelo.Medico;
import microservicios.odontologia.modelo.Paciente;

public class Cita implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("codigo")
	String codigo = "";
	
	@JsonProperty("tipoCita")
	int tipoCita;
	
	@JsonProperty("fecha")
	String fecha; //AAAAMMDD
	
	@JsonProperty("medico")
	Medico medico;
	
	@JsonProperty("paciente")
	Paciente paciente;
	
	@JsonProperty("clinica")
	String clinica;
	
	@JsonProperty("hora")
	int hora; //de 7 a 17
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
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
	public String getClinica() {
		return clinica;
	}
	
	public void setClinica(String clinica) {
		this.clinica = clinica;
	}	
	
	public int getTipoCita() {
		return tipoCita;
	}

	public void setTipoCita(int tipoCita) {
		this.tipoCita = tipoCita;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {		
		this.hora = hora;
	}

	@Override
	public String toString() {
		return "Cita [codigo=" + codigo + ", fecha=" + fecha + ", medico=" + medico + ", paciente=" + paciente
				+ ", clinica=" + clinica + "]";
	}	
}
