package microservicios.odontologia.modelo;

import java.io.Serializable;

public class Factura implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String cita;
	String valorCita;
	String idPaciente;
	String idMedico;
	String tipoCita;
	String centroCosto;
	
	/**
	 * @return the cita
	 */
	public String getCita() {
		return cita;
	}
	/**
	 * @param cita the cita to set
	 */
	public void setCita(String cita) {
		this.cita = cita;
	}
	/**
	 * @return the valorCita
	 */
	public String getValorCita() {
		return valorCita;
	}
	/**
	 * @param valorCita the valorCita to set
	 */
	public void setValorCita(String valorCita) {
		this.valorCita = valorCita;
	}
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
	 * @return the tipoCita
	 */
	public String getTipoCita() {
		return tipoCita;
	}
	/**
	 * @param tipoCita the tipoCita to set
	 */
	public void setTipoCita(String tipoCita) {
		this.tipoCita = tipoCita;
	}
	/**
	 * @return the centroCosto
	 */
	public String getCentroCosto() {
		return centroCosto;
	}
	/**
	 * @param centroCosto the centroCosto to set
	 */
	public void setCentroCosto(String centroCosto) {
		this.centroCosto = centroCosto;
	}
	
	

}
