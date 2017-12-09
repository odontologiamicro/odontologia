package microservicios.odontologia.modelo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Medico implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("idMedico")
	private long id;
	
	@JsonProperty("nombreMedico")
	private String nombre;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Medico [id=" + id + ", nombre=" + nombre + "]";
	}	
}
