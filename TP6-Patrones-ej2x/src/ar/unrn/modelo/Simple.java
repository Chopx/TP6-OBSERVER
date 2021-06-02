package ar.unrn.modelo;

public class Simple implements Medidores {
	private String temperatura;
	private ClimaOnline clima;

	public Simple(ClimaOnline clima) {
		this.clima = clima;
	}

	@Override
	public String leerTemperatura() {
		this.temperatura = this.clima.temperatura();
		return this.temperatura;
	}

}
