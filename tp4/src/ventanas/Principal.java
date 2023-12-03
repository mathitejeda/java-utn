package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnEjercicio1 = new JButton("Ejercicio 1");
		btnEjercicio1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ventana1 ejercicio1 = new Ventana1();
				ejercicio1.setVisible(true);
			}
		});
		btnEjercicio1.setBounds(163, 77, 100, 23);
		contentPane.add(btnEjercicio1);
		
		JButton btnEjercicio2 = new JButton("Ejercicio 2");
		btnEjercicio2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ventana2 ejercicio2 = new Ventana2();
				ejercicio2.setVisible(true);
			}
		});
		btnEjercicio2.setBounds(163, 111, 100, 23);
		contentPane.add(btnEjercicio2);
		
		JButton btnEjercicio3 = new JButton("Ejercicio 3");
		btnEjercicio3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ventana3 ejercicio3 = new Ventana3();
				ejercicio3.setVisible(true);
			}
		});
		btnEjercicio3.setBounds(163, 145, 100, 23);
		contentPane.add(btnEjercicio3);
		
		JLabel lblGrupoNro = new JLabel("GRUPO NRO: 6");
		lblGrupoNro.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGrupoNro.setBounds(54, 29, 110, 37);
		contentPane.add(lblGrupoNro);
	}

}
