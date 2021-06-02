package ar.unrn.parcial.persistance;

import java.util.ArrayList;

import ar.unrn.parcial.modelo.Factura;
import ar.unrn.parcial.modelo.RepositorioDeFacturas;

public class RegistroDeFacturasEnMemoria implements RepositorioDeFacturas {

	private ArrayList<Factura> facturas = new ArrayList();

	@Override
	public void registrarFactura(Factura factura) {
		facturas.add(factura);
	}

	@Override
	public ArrayList<Factura> obtenerFacturas() {
		return facturas;
	}

}
