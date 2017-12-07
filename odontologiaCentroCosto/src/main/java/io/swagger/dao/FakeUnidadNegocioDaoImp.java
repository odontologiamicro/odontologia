package io.swagger.dao;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import io.swagger.model.CentroCosto;
import io.swagger.model.UnidadDeNegocio;

@Repository
@Qualifier("fakeData")
public class FakeUnidadNegocioDaoImp implements UnidadNegocioDAO{

	private static HashMap<String, UnidadDeNegocio> data;

    static {
    	
    	final UnidadDeNegocio unidad1 = new UnidadDeNegocio();
    	unidad1.setIdUnidadDeNegocio("U001");
    	unidad1.setNombre("Unidad Negocio PPAL");
    	unidad1.setNombreEmpresa("Odontologia sede PPAL");
    	unidad1.setEstado("ACT");
    	
    	final UnidadDeNegocio unidad2 = new UnidadDeNegocio();
    	unidad2.setIdUnidadDeNegocio("U002");
    	unidad2.setNombre("Unidad Negocio Secundaria");
    	unidad2.setNombreEmpresa("Odontologia sede Auxiliar");
    	unidad2.setEstado("ACT");
    	
    	final UnidadDeNegocio unidad3 = new UnidadDeNegocio();
    	unidad3.setIdUnidadDeNegocio("U003");
    	unidad3.setNombre("Unidad Negocio Alerna");
    	unidad3.setNombreEmpresa("Odontologia sede Alterna");
    	unidad3.setEstado("ACT");

    	data = new HashMap<String, UnidadDeNegocio>(){

            {
                put("U001", unidad1);
                put("U002", unidad2);
                put("U003", unidad3);
            }
        };
    }
    
	@Override
	public Collection<UnidadDeNegocio> getUnidadesNegocio() {
		return this.data.values();
	}

	@Override
	public UnidadDeNegocio getUnidadNegocioById(String idUnidad) {
		return this.data.get(idUnidad);
	}

	@Override
	public void insertUnidadNegocio(UnidadDeNegocio unidad) {
		this.data.put(unidad.getIdUnidadDeNegocio(), unidad);
		
	}

}
