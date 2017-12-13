package io.swagger.dao;

import java.util.Collection;

import io.swagger.model.UnidadDeNegocio;

public interface UnidadNegocioDAO {

	Collection<UnidadDeNegocio> getUnidadesNegocio();

    UnidadDeNegocio getUnidadNegocioById(String idUnidad);

    void insertUnidadNegocio(UnidadDeNegocio unidad);
}
