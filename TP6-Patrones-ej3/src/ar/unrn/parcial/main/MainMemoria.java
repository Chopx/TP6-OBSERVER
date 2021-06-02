package ar.unrn.parcial.main;

import ar.unrn.mail.Mail;
import ar.unrn.parcial.modelo.EstacionDeServicio;
import ar.unrn.parcial.persistance.RegistroDeFacturasEnMemoria;
import ar.unrn.parcial.ui.UiPrincipal;

public class MainMemoria {

	public static void main(String[] args) {

		Mail empresa = new Mail();
		EstacionDeServicio estacion = new EstacionDeServicio(new RegistroDeFacturasEnMemoria());
		estacion.agregarObservador(empresa);
		UiPrincipal frame = new UiPrincipal(estacion);
		frame.setVisible(true);

	}
}
