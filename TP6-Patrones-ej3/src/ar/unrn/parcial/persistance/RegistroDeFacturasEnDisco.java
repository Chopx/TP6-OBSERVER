package ar.unrn.parcial.persistance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import ar.unrn.parcial.modelo.Combustible;
import ar.unrn.parcial.modelo.Comun;
import ar.unrn.parcial.modelo.Email;
import ar.unrn.parcial.modelo.Factura;
import ar.unrn.parcial.modelo.Litros;
import ar.unrn.parcial.modelo.RepositorioDeFacturas;
import ar.unrn.parcial.modelo.Super;

public class RegistroDeFacturasEnDisco implements RepositorioDeFacturas {

	private String nombreDelArchivo;

	// Corrección:
	// los path de los archivos para las implementacion de acceso a disco,
	// deben inyectarse por constructor y no estar dura en el medio de un método de
	// la clase.

	public RegistroDeFacturasEnDisco(String nombreArchivo) {
		this.nombreDelArchivo = nombreArchivo;
	}

	@Override
	public void registrarFactura(Factura factura) {

		String fechaFormateada = factura.fechaDeFactura().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		String horaFormateada = factura.fechaDeFactura().format(DateTimeFormatter.ofPattern("h:mm"));
		String combustible = factura.verTipoDeCombustible();
		String litrosTotal = Integer.toString(factura.cantidadDeLitros());
		String monto = Double.toString(factura.obtenerMonto());
		String email = factura.verEmail();
		String stringFactura = fechaFormateada + "," + horaFormateada + "," + combustible + "," + litrosTotal + ","
				+ monto + "," + email + "\n";
		try {
			Files.write(Paths.get(this.nombreDelArchivo), stringFactura.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			throw new RuntimeException("No se pudo persistir...", e);
		}
	}

	@Override
	public ArrayList<Factura> obtenerFacturas() throws Exception {
		try {
			List<String> lineaCadaFactura = Files.readAllLines(Paths.get(this.nombreDelArchivo));
			ArrayList<Factura> facturas = new ArrayList<Factura>();
			Combustible tipoCombustible = null;
			for (String s : lineaCadaFactura) {
				String[] split = s.split(",");
				String fecha = split[0];
				String[] split2 = fecha.split("-");
				String hora = split[1];
				String[] split3 = hora.split(":");
				LocalDateTime fechaDate = LocalDateTime.of(Integer.parseInt(split2[2]), Integer.parseInt(split2[1]),
						Integer.parseInt(split2[0]), Integer.parseInt(split3[0]), Integer.parseInt(split3[1]));
				if (split[2].equals("Super"))
					tipoCombustible = new Super(90);
				else
					tipoCombustible = new Comun(70);
				Litros litros = new Litros(Integer.parseInt(split[3]));
				Email email = new Email(split[5]);
				Factura fact = new Factura(fechaDate, tipoCombustible, litros, Double.parseDouble(split[4]), email);
				facturas.add(fact);

			}

			return facturas;

		} catch (IOException e) {
			System.out.println("No se pueden obtener las facturas desde el archivo.");
		}

		return null;
	}

}
