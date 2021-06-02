package ar.unrn.parcial.main;

import ar.unrn.parcial.modelo.EstacionDeServicio;
import ar.unrn.parcial.persistance.RegistroDeFacturasEnDisco;
import ar.unrn.parcial.ui.UiPrincipal;

public class MainArchivoTexto {

	public static void main(String[] args) {

		EstacionDeServicio estacion = new EstacionDeServicio(
				new RegistroDeFacturasEnDisco("C:\\Users\\ELJUEEN\\Desktop\\archivoParcial.txt"));
		UiPrincipal frame = new UiPrincipal(estacion);
		frame.setVisible(true);

	}
}
