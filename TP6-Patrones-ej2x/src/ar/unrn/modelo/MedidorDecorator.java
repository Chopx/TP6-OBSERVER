package ar.unrn.modelo;

import java.util.List;

public class MedidorDecorator extends Observable implements Medidores {

	private Medidores medidor;

	public MedidorDecorator(Medidores medidor, List<Observer> monitor) {
		this.medidor = medidor;
		for (Observer observer : monitor) {
			this.agregarObservador(observer);
		}

	}

	@Override
	public String leerTemperatura() {

		String medidorxd = medidor.leerTemperatura();
		this.notificar(medidorxd);
		return medidorxd;
	}

}
