package io.swagger.dao;

import java.util.Collection;

import io.swagger.model.CentroCosto;

public interface CentroCostoDAO {
	
	Collection<CentroCosto> getCentrosCostos();

    CentroCosto getCentroCostoById(String idCentroCosto);

    void insertCentroCosto(CentroCosto centroCosto);

}
