package ar.unrn.parcial.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.unrn.parcial.modelo.EstacionDeServicio;

public class UiPrincipal extends JFrame {

	private JPanel contentPane;
	private EstacionDeServicio estacion;

	public UiPrincipal(EstacionDeServicio estacion) {
		setTitle("Estacion de servicio YPZW");
		this.estacion = estacion;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 221);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Consultar Ventas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UiVentas vistaVentas = new UiVentas(estacion);
				vistaVentas.setVisible(true);
			}
		});
		btnNewButton.setBounds(246, 56, 139, 61);
		contentPane.add(btnNewButton);

		JButton Boton_Cargar_Combustible = new JButton("Cargar Combustible");
		Boton_Cargar_Combustible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UiCombustible vistaCargarCombustible = new UiCombustible(estacion);
				vistaCargarCombustible.setVisible(true);
			}
		});
		Boton_Cargar_Combustible.setBounds(54, 56, 139, 61);
		contentPane.add(Boton_Cargar_Combustible);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		btnSalir.setBounds(173, 141, 90, 30);
		contentPane.add(btnSalir);
	}
}
