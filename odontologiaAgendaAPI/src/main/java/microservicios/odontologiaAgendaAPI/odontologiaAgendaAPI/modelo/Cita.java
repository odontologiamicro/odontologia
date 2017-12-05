package microservicios.odontologiaAgendaAPI.odontologiaAgendaAPI.modelo;

import java.io.Serializable;

public class Cita implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String codigo;
	String fecha;
	String odontologo;
	String paciente;
	String clinica;
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
	public String getOdontologo() {
		return odontologo;
	}
	public void setOdontologo(String odontologo) {
		this.odontologo = odontologo;
	}
	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	public String getClinica() {
		return clinica;
	}
	public void setClinica(String clinica) {
		this.clinica = clinica;
	}
	
	
}
