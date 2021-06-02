package ar.unrn.parcial.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EstacionDeServicio extends Observable {

	private RepositorioDeFacturas repo;

	public EstacionDeServicio(RepositorioDeFacturas repo) {
		this.repo = repo;
	}

	public void pagarFactura(LocalDateTime fecha, Combustible tipoDeCombustible, int litros, String email)
			throws ValidarLitrosException, ValidarEmailException {
		Litros litrosTotal = new Litros(litros);
		Email correo = new Email(email);
		Factura factura = new Factura(fecha, tipoDeCombustible, litrosTotal,
				tipoDeCombustible.aplicarDescuento(fecha, litros), correo);
		repo.registrarFactura(factura);

		Thread hilo = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					notificar(factura);
				} catch (ValidarLitrosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		hilo.start();

	}

	public double verPrecio(LocalDateTime fecha, Combustible tipoDeCombustible, int litros)
			throws ValidarLitrosException, ValidarEmailException {
		Litros litrosTotal = new Litros(litros);
		Email correo = new Email("ejemplo@gmail.com");
		Factura factura = new Factura(fecha, tipoDeCombustible, litrosTotal,
				tipoDeCombustible.aplicarDescuento(fecha, litros), correo);
		return factura.obtenerMonto();
	}

	public ArrayList<Factura> obtenerFacturasEntreFechas(LocalDateTime inicio, LocalDateTime fin) throws Exception {
		ArrayList<Factura> facturas = repo.obtenerFacturas();
		ArrayList<Factura> facturasEntreFechas = new ArrayList<Factura>();

		for (Factura f : facturas) {
			if (estaEnElRangoDeFechas(inicio, fin, f.fechaDeFactura())) {
				facturasEntreFechas.add(f);
			}
		}
		return facturasEntreFechas;
	}

	private boolean estaEnElRangoDeFechas(LocalDateTime inicio, LocalDateTime fin, LocalDateTime fechaFactura) {
		if ((fechaFactura.isAfter(inicio) || fechaFactura.isEqual(inicio))
				&& (fechaFactura.isBefore(fin) || fechaFactura.isEqual(fin)))
			return true;
		return false;
	}

}
