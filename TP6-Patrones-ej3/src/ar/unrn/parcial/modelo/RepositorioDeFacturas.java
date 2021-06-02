package ar.unrn.parcial.modelo;

import java.util.ArrayList;

public interface RepositorioDeFacturas {

	void registrarFactura(Factura factura) throws ValidarLitrosException;

	ArrayList<Factura> obtenerFacturas() throws Exception;

}
