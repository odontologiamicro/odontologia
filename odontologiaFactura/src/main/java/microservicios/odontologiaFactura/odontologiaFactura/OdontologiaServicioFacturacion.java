package microservicios.odontologiaFactura.odontologiaFactura;

import java.util.HashMap;

import microservicios.odontologia.modelo.Factura;

public class OdontologiaServicioFacturacion {
	
	public static HashMap<String, Factura> listaFacturas = new HashMap<>();
	
	public static Factura getFactura(String datosFactura) {
		return listaFacturas.get(datosFactura);
	}
	
	public static void addFactura(String claveFactura, Factura factura) {
		listaFacturas.put(claveFactura, factura);
	}

}
