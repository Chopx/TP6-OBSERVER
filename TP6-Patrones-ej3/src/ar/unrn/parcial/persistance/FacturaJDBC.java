package ar.unrn.parcial.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import ar.unrn.parcial.modelo.Combustible;
import ar.unrn.parcial.modelo.Comun;
import ar.unrn.parcial.modelo.Email;
import ar.unrn.parcial.modelo.Factura;
import ar.unrn.parcial.modelo.Litros;
import ar.unrn.parcial.modelo.RepositorioDeFacturas;
import ar.unrn.parcial.modelo.Super;
import ar.unrn.parcial.modelo.ValidarLitrosException;

public class FacturaJDBC implements RepositorioDeFacturas {

	private Connection dbConn;

	@Override
	public void registrarFactura(Factura factura) throws ValidarLitrosException {
		// TODO Auto-generated method stub
		conectarConBaseDeDatos();
		try {
			PreparedStatement st = dbConn.prepareStatement(
					"insert into facturas(fecha, combustible, litros, monto, email) values (?,?,?,?,?)");

			st.setTimestamp(1, Timestamp.valueOf(factura.fechaDeFactura()));
			st.setString(2, factura.verTipoDeCombustible());
			st.setInt(3, factura.cantidadDeLitros());
			st.setDouble(4, factura.obtenerMonto());
			st.setString(5, factura.verEmail());
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			throw new RuntimeException("Error en cargar factura");
		}
	}

	@Override
	public ArrayList<Factura> obtenerFacturas() throws Exception {
		conectarConBaseDeDatos();
		try {
			ArrayList<Factura> list = new ArrayList<Factura>();
			Combustible tipoCombustible = null;
			Statement st = dbConn.createStatement();
			ResultSet resul = st.executeQuery("select * from facturas F;");
			while (resul.next()) {
				LocalDateTime f = resul.getTimestamp("F.fecha").toLocalDateTime();
				Litros litros = new Litros(resul.getInt("F.litros"));
				Email email = new Email(resul.getString("F.email"));
				if (resul.getString("F.combustible").equals("Super"))
					tipoCombustible = new Super(90);
				else
					tipoCombustible = new Comun(70);

				list.add(new Factura(f, tipoCombustible, litros, resul.getDouble("F.monto"), email));
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException("Error en cargar factura");
		}

	}

	private void conectarConBaseDeDatos() {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/parcialoo2";
		String user = "root";
		String password = "";
		try {
			this.dbConn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
