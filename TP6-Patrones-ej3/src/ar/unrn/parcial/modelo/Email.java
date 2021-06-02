package ar.unrn.parcial.modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {

	private String email;

	public Email(String email) throws ValidarEmailException {
		if (email == null)
			throw new ValidarEmailException("Debe ingresar un email.");
		if (email.isEmpty())
			throw new ValidarEmailException("Debe ingresar un email.");
		if (!emailEsValido(email))
			throw new ValidarEmailException("Debe ingresar un email valido. Ej: juan@gmail.com ");

		this.email = email;
	}

	public String verEmail() {
		return email;
	}

	public static Boolean emailEsValido(String email) {
		Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

}
