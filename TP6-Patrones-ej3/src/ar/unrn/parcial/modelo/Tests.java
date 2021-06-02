package ar.unrn.parcial.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import ar.unrn.parcial.persistance.RegistroDeFacturasEnMemoria;

class Tests {

	@Test
	void facturaDescuentoNaftaSuperDomingoTest() throws Exception {
		// set up
		RepositorioDeFacturas repo = new RegistroDeFacturasEnMemoria();
		EstacionDeServicio estacion = new EstacionDeServicio(repo);
		LocalDateTime FechaDomingo = LocalDateTime.of(2021, 5, 9, 9, 0);
		Combustible naftaSuper = new Super(90);
		Litros veinteLts = new Litros(20);

		double montoTotal = estacion.verPrecio(FechaDomingo, naftaSuper, veinteLts.verCantidadDeLitros());

		// verify
		assertEquals(montoTotal, 1620.0, 0.1);

	}

	@Test
	void facturaDescuentoNaftaSuperSabadoTest() throws Exception {
		// set up
		RepositorioDeFacturas repo = new RegistroDeFacturasEnMemoria();
		EstacionDeServicio estacion = new EstacionDeServicio(repo);
		LocalDateTime FechaSabado = LocalDateTime.of(2021, 5, 8, 9, 0);
		Combustible naftaSuper = new Super(90);
		Litros veinteLts = new Litros(20);

		// exercise
		double montoTotal = estacion.verPrecio(FechaSabado, naftaSuper, veinteLts.verCantidadDeLitros());

		// verify
		assertEquals(montoTotal, 1584.0, 0.1);

	}

	@Test
	void facturaDescuentoNaftaComunEnHorarioTest() throws Exception {
		// set up
		RepositorioDeFacturas repo = new RegistroDeFacturasEnMemoria();
		EstacionDeServicio estacion = new EstacionDeServicio(repo);
		LocalDateTime Fecha = LocalDateTime.of(2021, 5, 8, 9, 0);
		Combustible naftaComun = new Comun(70);
		Litros veinteLts = new Litros(20);

		// exercise
		double montoTotal = estacion.verPrecio(Fecha, naftaComun, veinteLts.verCantidadDeLitros());

		// verify
		assertEquals(montoTotal, 1330.0, 0.1);

	}

	@Test
	void facturaSinDescuento() throws Exception {
		// set up
		RepositorioDeFacturas repo = new RegistroDeFacturasEnMemoria();
		EstacionDeServicio estacion = new EstacionDeServicio(repo);
		LocalDateTime Fecha = LocalDateTime.of(2021, 5, 8, 16, 0);
		Combustible naftaComun = new Comun(70);
		Litros veinteLts = new Litros(20);

		// exercise
		double montoTotal = estacion.verPrecio(Fecha, naftaComun, veinteLts.verCantidadDeLitros());

		// verify
		assertEquals(montoTotal, 1400.0, 0.1);

	}

	@Test
	void facturaSinDescuentoPorLlevarMenosDe20Lts() throws Exception {
		// set up
		RepositorioDeFacturas repo = new RegistroDeFacturasEnMemoria();
		EstacionDeServicio estacion = new EstacionDeServicio(repo);
		LocalDateTime FechaSabado = LocalDateTime.of(2021, 5, 8, 9, 0);
		Combustible naftaSuper = new Super(90);
		Litros veinteLts = new Litros(10);

		// exercise
		double montoTotal = estacion.verPrecio(FechaSabado, naftaSuper, veinteLts.verCantidadDeLitros());

		// verify
		assertEquals(montoTotal, 900.0, 0.1);

	}
}
