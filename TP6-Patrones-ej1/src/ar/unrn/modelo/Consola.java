package ar.unrn.modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Spliterator;

public class Consola implements Observer{

	
	public Consola () {
		
	}

	@Override
	public void actualizar(String valor) {
		LocalDateTime hoy = LocalDateTime.now();
		String fechaTemperatura = hoy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy H:m:s")) + " Temperatura: " + valor + "\n";
		
		String[] split = valor.split(" ");
        int temp = Integer.parseInt(split[0]);
        
        if (temp < 12)
        	System.out.println( fechaTemperatura + " Hace frío, se encenderá la caldera");
        if (temp > 17)
        	System.out.println( fechaTemperatura + " Hace calor, se encenderá el aire acondicionado");
		
		
	}
}