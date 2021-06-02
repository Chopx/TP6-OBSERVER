package ar.unrn.parcial.modelo;

import java.time.LocalDateTime;

public class Factura {

	private LocalDateTime fecha;
	private Combustible combustible;
	private Litros litros;
	private double monto;
	private Email email;

	public Factura(LocalDateTime fecha, Combustible combustible, Litros litros, double monto, Email email) {
		this.fecha = fecha;
		this.combustible = combustible;
		this.litros = litros;
		this.monto = monto;
		this.email = email;
	}

	public LocalDateTime fechaDeFactura() {
		return fecha;
	}

	public int cantidadDeLitros() {
		return litros.verCantidadDeLitros();
	}

	public double obtenerMonto() {
		return monto;
	}

	public String verTipoDeCombustible() {
		return combustible.verTipoDeCombustible();
	}

	public Combustible obtenerCombustible() {
		return this.combustible;
	}

	public String verEmail() {
		return email.verEmail();
	}

	@Override
	public String toString() {
		return fecha.toLocalDate() + "," + combustible.verTipoDeCombustible() + "," + litros.verCantidadDeLitros() + ","
				+ obtenerMonto() + "," + email.verEmail() + "\n";
	}
}
