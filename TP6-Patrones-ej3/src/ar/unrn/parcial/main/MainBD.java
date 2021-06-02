package ar.unrn.parcial.main;

import ar.unrn.parcial.ui.UiPrincipal;
import ar.unrn.parcial.modelo.EstacionDeServicio;
import ar.unrn.parcial.persistance.FacturaJDBC;

public class MainBD {

	public static void main(String[] args) {

		EstacionDeServicio estacion = new EstacionDeServicio(new FacturaJDBC());
		UiPrincipal frame = new UiPrincipal(estacion);
		frame.setVisible(true);

	}

}
