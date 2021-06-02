package ar.unrn.parcial.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ar.unrn.parcial.modelo.Combustible;
import ar.unrn.parcial.modelo.Comun;
import ar.unrn.parcial.modelo.EstacionDeServicio;
import ar.unrn.parcial.modelo.Super;
import ar.unrn.parcial.modelo.ValidarEmailException;
import ar.unrn.parcial.modelo.ValidarLitrosException;

public class UiCombustible extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField TextFieldemail;

	public UiCombustible(EstacionDeServicio estacion) {

		setTitle("Cargar Combustible");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 390, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(39, 14, 83, 14);
		contentPane.add(lblEmail);

		TextFieldemail = new JTextField();
		TextFieldemail.setColumns(10);
		TextFieldemail.setBounds(147, 11, 189, 20);
		contentPane.add(TextFieldemail);

		textField = new JTextField();
		textField.setBounds(147, 42, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel Litros = new JLabel("Litros cargados:");
		Litros.setBounds(39, 45, 83, 14);
		contentPane.add(Litros);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(147, 73, 118, 22);
		contentPane.add(comboBox);
		comboBox.addItem("Comun");
		comboBox.addItem("Super");

		JLabel lblTipoDeNafta = new JLabel("Tipo de nafta:");
		lblTipoDeNafta.setBounds(39, 77, 83, 14);
		contentPane.add(lblTipoDeNafta);

		JButton Consultar_pago = new JButton("Consultar pago");
		Consultar_pago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDateTime hoy = LocalDateTime.now();
				Combustible tipoCombustible = tipoDeCombustible(comboBox);
				double monto;
				try {
					monto = estacion.verPrecio(hoy, tipoCombustible, Integer.parseInt(textField.getText()));
					JOptionPane.showMessageDialog(null, "El monto es de: " + monto, "Informe monto",
							JOptionPane.OK_CANCEL_OPTION);
				} catch (NumberFormatException | ValidarLitrosException | ValidarEmailException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		Consultar_pago.setBounds(58, 126, 111, 38);
		contentPane.add(Consultar_pago);

		JButton Consultar_confirmar_pago = new JButton("Confirmar pago");
		Consultar_confirmar_pago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LocalDateTime hoy = LocalDateTime.now();
				Combustible tipoCombustible = tipoDeCombustible(comboBox);
				try {
					estacion.pagarFactura(hoy, tipoCombustible, Integer.parseInt(textField.getText()),
							TextFieldemail.getText());
					dispose();

					JOptionPane.showMessageDialog(null, "Se agregó la factura exitosamente", "Registro de factura",
							JOptionPane.OK_CANCEL_OPTION);
				} catch (ValidarLitrosException | ValidarEmailException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		Consultar_confirmar_pago.setBounds(205, 126, 118, 38);
		contentPane.add(Consultar_confirmar_pago);

		JButton Consultar_confirmar_pago_1 = new JButton("Atras");
		Consultar_confirmar_pago_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		Consultar_confirmar_pago_1.setBounds(293, 187, 71, 23);
		contentPane.add(Consultar_confirmar_pago_1);

	}

	private Combustible tipoDeCombustible(JComboBox box) {
		Combustible tipoCombustible = null;

		if (box.getSelectedItem().toString().equals("Comun"))
			return tipoCombustible = new Comun(70);

		return tipoCombustible = new Super(90);
	}
}
