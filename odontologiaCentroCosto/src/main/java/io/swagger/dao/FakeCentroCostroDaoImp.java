package io.swagger.dao;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import io.swagger.model.CentroCosto;
import io.swagger.model.UnidadDeNegocio;

@Repository
@Qualifier("fakeData")

public class FakeCentroCostroDaoImp implements CentroCostoDAO {

	private static HashMap<String, CentroCosto> data;

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
    	
    	final CentroCosto centroCosto = new CentroCosto();
    	centroCosto.setIdCentroCosto("C001");
    	centroCosto.setNombre("Centro Costos Principal");
    	centroCosto.setDescripcion("Cobros citas Prioritarias");
    	centroCosto.setEstado("ACT");
    	centroCosto.setUnidadDeNegocio(unidad1);
    	
    	final CentroCosto centroCosto2 = new CentroCosto();
    	centroCosto2.setIdCentroCosto("C002");
    	centroCosto2.setNombre("Centro Costos Auxiliar");
    	centroCosto2.setDescripcion("Cobros citas Programadas");
    	centroCosto2.setEstado("ACT");
    	centroCosto2.setUnidadDeNegocio(unidad2);
    	
    	final CentroCosto centroCosto3 = new CentroCosto();
    	centroCosto3.setIdCentroCosto("C003");
    	centroCosto3.setNombre("Centro Costos Alterno");
    	centroCosto3.setDescripcion("Cobros citas Emergencia");
    	centroCosto3.setEstado("ACT");
    	centroCosto3.setUnidadDeNegocio(unidad3);
    	

    	data = new HashMap<String, CentroCosto>(){

            {
                put("C001", centroCosto);
                put("C002", centroCosto2);
                put("C003", centroCosto3);
            }
        };
    }
    
	@Override
	public Collection<CentroCosto> getCentrosCostos() {
		return this.data.values();
	}

	@Override
	public CentroCosto getCentroCostoById(String idCentroCosto) {
		return this.data.get(idCentroCosto);
	}

	@Override
	public void insertCentroCosto(CentroCosto centroCosto) {
		this.data.put(centroCosto.getIdCentroCosto(), centroCosto);
		
	}

}
