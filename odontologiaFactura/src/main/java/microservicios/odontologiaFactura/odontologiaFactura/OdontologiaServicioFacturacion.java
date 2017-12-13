package microservicios.odontologiaFactura.odontologiaFactura;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import microservicios.odontologia.modelo.Factura;

public class OdontologiaServicioFacturacion {
	
	public static ConcurrentMap<String, Factura> listaFacturas = new ConcurrentHashMap<>();
	
	public static Factura getFactura(String datosFactura) {
		if( datosFactura != null )
			return listaFacturas.get(datosFactura);
		return new Factura();
	}
	
	public static void addFactura(String claveFactura, Factura factura) {
		listaFacturas.put(claveFactura, factura);
	}

}
