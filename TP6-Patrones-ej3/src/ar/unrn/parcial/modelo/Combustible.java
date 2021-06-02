package ar.unrn.parcial.modelo;

import java.time.LocalDateTime;

public abstract class Combustible {

	private double precio;

	public Combustible(double precio) {
		this.precio = precio;
	}

	public double obtenerPrecio() {
		return precio;
	}

	public double calcularPrecio(int litros) {
		return this.precio * litros;
	}

	public abstract double aplicarDescuento(LocalDateTime fecha, int cantidadLitros);

	public abstract String verTipoDeCombustible();
}
