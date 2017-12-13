package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * CentroCosto
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-07T03:36:42.350Z")

public class CentroCosto extends ResourceSupport {
  @JsonProperty("idCentroCosto")
  private String idCentroCosto = null;

  @JsonProperty("nombre")
  private String nombre = null;

  @JsonProperty("descripcion")
  private String descripcion = null;

  @JsonProperty("estado")
  private String estado = null;

  @JsonProperty("unidadDeNegocio")
  private UnidadDeNegocio unidadDeNegocio = null;

  public CentroCosto idCentroCosto(String idCentroCosto) {
    this.idCentroCosto = idCentroCosto;
    return this;
  }

   /**
   * Get idCentroCosto
   * @return idCentroCosto
  **/
  @ApiModelProperty(example = "C001", required = true, value = "")
  @NotNull


  public String getIdCentroCosto() {
    return idCentroCosto;
  }

  public void setIdCentroCosto(String idCentroCosto) {
    this.idCentroCosto = idCentroCosto;
  }

  public CentroCosto nombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

   /**
   * Get nombre
   * @return nombre
  **/
  @ApiModelProperty(example = "Centro de Costos General", required = true, value = "")
  @NotNull


  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public CentroCosto descripcion(String descripcion) {
    this.descripcion = descripcion;
    return this;
  }

   /**
   * Get descripcion
   * @return descripcion
  **/
  @ApiModelProperty(example = "Centro de Costos General", required = true, value = "")
  @NotNull


  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public CentroCosto estado(String estado) {
    this.estado = estado;
    return this;
  }

   /**
   * Get estado
   * @return estado
  **/
  @ApiModelProperty(example = "Activo", required = true, value = "")
  @NotNull


  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public CentroCosto unidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
    this.unidadDeNegocio = unidadDeNegocio;
    return this;
  }

   /**
   * Get unidadDeNegocio
   * @return unidadDeNegocio
  **/
  @ApiModelProperty(value = "")

  @Valid

  public UnidadDeNegocio getUnidadDeNegocio() {
    return unidadDeNegocio;
  }

  public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
    this.unidadDeNegocio = unidadDeNegocio;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CentroCosto centroCosto = (CentroCosto) o;
    return Objects.equals(this.idCentroCosto, centroCosto.idCentroCosto) &&
        Objects.equals(this.nombre, centroCosto.nombre) &&
        Objects.equals(this.descripcion, centroCosto.descripcion) &&
        Objects.equals(this.estado, centroCosto.estado) &&
        Objects.equals(this.unidadDeNegocio, centroCosto.unidadDeNegocio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idCentroCosto, nombre, descripcion, estado, unidadDeNegocio);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CentroCosto {\n");
    
    sb.append("    idCentroCosto: ").append(toIndentedString(idCentroCosto)).append("\n");
    sb.append("    nombre: ").append(toIndentedString(nombre)).append("\n");
    sb.append("    descripcion: ").append(toIndentedString(descripcion)).append("\n");
    sb.append("    estado: ").append(toIndentedString(estado)).append("\n");
    sb.append("    unidadDeNegocio: ").append(toIndentedString(unidadDeNegocio)).append("\n");
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

