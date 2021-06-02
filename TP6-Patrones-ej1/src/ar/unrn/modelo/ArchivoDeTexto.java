package ar.unrn.modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ArchivoDeTexto implements Observer{

	private String ruta;
	
	public ArchivoDeTexto (String ruta) {
		this.ruta = ruta;
	}

	@Override
	public void actualizar(String valor) {
		LocalDateTime hoy = LocalDateTime.now();
		String fechaTemperatura = hoy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy H:m:s")) + " Temperatura: " + valor + "\n";
		try {
			FileWriter escritura = new FileWriter(new File(ruta), true);
			escritura.write(fechaTemperatura);
			escritura.close();
			
		} catch (IOException e) {
			throw new RuntimeException("No se pudo persistir..." + e.getLocalizedMessage(), e);
		}
	}
}
