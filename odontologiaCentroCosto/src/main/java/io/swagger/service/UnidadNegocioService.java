package io.swagger.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import io.swagger.dao.CentroCostoDAO;
import io.swagger.dao.UnidadNegocioDAO;
import io.swagger.model.UnidadDeNegocio;

@Service
public class UnidadNegocioService {
	
	@Autowired
    @Qualifier("fakeData")
	private UnidadNegocioDAO unidadNegocioDao;
	
	public Collection<UnidadDeNegocio> getUnidadesNegocio(){
		return unidadNegocioDao.getUnidadesNegocio();
	}

    public UnidadDeNegocio getUnidadNegocioById(String idUnidad) {
    		return unidadNegocioDao.getUnidadNegocioById(idUnidad);
    }

    public void insertUnidadNegocio(UnidadDeNegocio unidad) {
    		unidadNegocioDao.insertUnidadNegocio(unidad);
    }

}
