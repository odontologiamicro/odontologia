package io.swagger.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import io.swagger.dao.CentroCostoDAO;
import io.swagger.model.CentroCosto;

@Service
public class CentroCostoService {

	@Autowired
    @Qualifier("fakeData")
    private CentroCostoDAO centroCostosDao;
	
	public Collection<CentroCosto> getCentrosCostos(){
		return centroCostosDao.getCentrosCostos();
	}

    public CentroCosto getCentroCostoById(String idCentroCosto) {
    		return centroCostosDao.getCentroCostoById(idCentroCosto);
    }

    public void insertCentroCosto(CentroCosto centroCosto) {
    		centroCostosDao.insertCentroCosto(centroCosto);
    }
	
}
