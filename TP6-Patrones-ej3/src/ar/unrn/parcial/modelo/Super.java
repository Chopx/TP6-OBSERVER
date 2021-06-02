package ar.unrn.parcial.modelo;

import java.time.LocalDateTime;

public class Super extends Combustible {

	public Super(double precio) {
		super(precio);
	}

	public double aplicarDescuento(LocalDateTime fecha, int cantidadLitros) {

		if (esSabado(fecha) && llevaMasDe20Lts(cantidadLitros))
			return calcularPrecio(cantidadLitros) - (calcularPrecio(cantidadLitros) * 0.12);
		if (esDomingo(fecha))
			return calcularPrecio(cantidadLitros) - (calcularPrecio(cantidadLitros) * 0.10);

		return calcularPrecio(cantidadLitros);
	}

	private boolean esDomingo(LocalDateTime fecha) {
		if (fecha.getDayOfWeek().toString().equals("SUNDAY"))
			return true;
		return false;
	}

	private boolean esSabado(LocalDateTime fecha) {
		if (fecha.getDayOfWeek().toString().equals("SATURDAY"))
			return true;
		return false;
	}

	private boolean llevaMasDe20Lts(int litros) {
		return (litros > 19);
	}

	public String verTipoDeCombustible() {
		return "Super";
	}

}
