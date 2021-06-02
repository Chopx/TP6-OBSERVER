package ar.unrn.modelo;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		ClimaOnline climaonline = new WeatherChannelService();
		ArrayList<Observer> lista = new ArrayList<Observer>();
		lista.add(new ArchivoDeTexto("C:\\Users\\ELJUEEN\\Desktop\\archivoMedidor.txt"));
		lista.add(new Consola());

		Simple medidor = new Simple(climaonline);
		medidor.leerTemperatura();
		medidor.leerTemperatura();

		MedidorDecorator medidorCompleto = new MedidorDecorator(medidor, lista);
		medidorCompleto.leerTemperatura();

	}

}
