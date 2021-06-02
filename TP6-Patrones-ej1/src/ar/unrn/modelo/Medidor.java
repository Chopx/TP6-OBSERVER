package ar.unrn.modelo;

import java.util.List;

public class Medidor extends Observable {
	private String temperatura;
	private ClimaOnline clima;

	public Medidor(ClimaOnline clima, List<Observer> monitor) {
		this.clima = clima;
		for (Observer observer : monitor) {
			this.agregarObservador(observer);
		}
	}

	public String leerTemperatura() {
		// leo la temperatura del servicio web
		this.temperatura = this.clima.temperatura();
		this.notificar(this.temperatura);
		return this.temperatura;
	}
}
