package ar.unrn.parcial.modelo;

import java.time.LocalDateTime;

public class Comun extends Combustible {

	public Comun(double precio) {
		super(precio);
	}

	public double aplicarDescuento(LocalDateTime fecha, int cantidadLitros) {

		if (esHorarioDeDescuento(fecha))
			return calcularPrecio(cantidadLitros) - (calcularPrecio(cantidadLitros) * 0.05);

		return calcularPrecio(cantidadLitros);
	}

	private boolean esHorarioDeDescuento(LocalDateTime fecha) {
		if (fecha.getHour() > 07 && fecha.getHour() < 10)
			return true;
		return false;
	}

	public String verTipoDeCombustible() {
		return "Comun";
	}

}
