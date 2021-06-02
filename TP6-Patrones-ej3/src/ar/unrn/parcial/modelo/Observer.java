package ar.unrn.parcial.modelo;

public interface Observer {
	void actualizar(Factura factura) throws ValidarLitrosException;
}
