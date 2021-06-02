package ar.unrn.parcial.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {

	private List<Observer> observadores;

	public Observable() {
		observadores = new ArrayList<>();
	}

	public void agregarObservador(Observer obs) {
		this.observadores.add(obs);
	}

	// Recibe factura
	protected void notificar(Factura factura) throws ValidarLitrosException {
		for (Observer observer : observadores) {
			observer.actualizar(factura);
		}
	}
}
