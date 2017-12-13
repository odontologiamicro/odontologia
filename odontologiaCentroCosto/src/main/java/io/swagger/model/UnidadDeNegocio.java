package io.swagger.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * UnidadDeNegocio
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-07T03:36:42.350Z")

public class UnidadDeNegocio extends ResourceSupport  {
  @JsonProperty("idUnidadDeNegocio")
  private String idUnidadDeNegocio = null;

  @JsonProperty("nombre")
  private String nombre = null;

  @JsonProperty("estado")
  private String estado = null;

  @JsonProperty("nombreEmpresa")
  private String nombreEmpresa = null;

  public UnidadDeNegocio idUnidadDeNegocio(String idUnidadDeNegocio) {
    this.idUnidadDeNegocio = idUnidadDeNegocio;
    return this;
  }

   /**
   * Get idUnidadDeNegocio
   * @return idUnidadDeNegocio
  **/
  @ApiModelProperty(example = "UN001", required = true, value = "")
  @NotNull


  public String getIdUnidadDeNegocio() {
    return idUnidadDeNegocio;
  }

  public void setIdUnidadDeNegocio(String idUnidadDeNegocio) {
    this.idUnidadDeNegocio = idUnidadDeNegocio;
  }

  public UnidadDeNegocio nombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

   /**
   * Get nombre
   * @return nombre
  **/
  @ApiModelProperty(example = "Unidad de Negocio Medica", required = true, value = "")
  @NotNull


  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public UnidadDeNegocio estado(String estado) {
    this.estado = estado;
    return this;
  }

   /**
   * Get estado
   * @return estado
  **/
  @ApiModelProperty(example = "Activa", required = true, value = "")
  @NotNull


  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public UnidadDeNegocio nombreEmpresa(String nombreEmpresa) {
    this.nombreEmpresa = nombreEmpresa;
    return this;
  }

   /**
   * Get nombreEmpresa
   * @return nombreEmpresa
  **/
  @ApiModelProperty(example = "Odontologia SA", required = true, value = "")
  @NotNull


  public String getNombreEmpresa() {
    return nombreEmpresa;
  }

  public void setNombreEmpresa(String nombreEmpresa) {
    this.nombreEmpresa = nombreEmpresa;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UnidadDeNegocio unidadDeNegocio = (UnidadDeNegocio) o;
    return Objects.equals(this.idUnidadDeNegocio, unidadDeNegocio.idUnidadDeNegocio) &&
        Objects.equals(this.nombre, unidadDeNegocio.nombre) &&
        Objects.equals(this.estado, unidadDeNegocio.estado) &&
        Objects.equals(this.nombreEmpresa, unidadDeNegocio.nombreEmpresa);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idUnidadDeNegocio, nombre, estado, nombreEmpresa);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UnidadDeNegocio {\n");
    
    sb.append("    idUnidadDeNegocio: ").append(toIndentedString(idUnidadDeNegocio)).append("\n");
    sb.append("    nombre: ").append(toIndentedString(nombre)).append("\n");
    sb.append("    estado: ").append(toIndentedString(estado)).append("\n");
    sb.append("    nombreEmpresa: ").append(toIndentedString(nombreEmpresa)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

