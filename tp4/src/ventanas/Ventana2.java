package ventanas;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana2 extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNota1;
	private JTextField textFieldNota2;
	private JTextField textFieldNota3;
	private JTextField textFieldPromedio;
	private JTextField textFieldCondicion;

	public Ventana2() {
		setTitle("Promedio");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 559, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelNotas = new JPanel();
		panelNotas.setBounds(47, 29, 340, 203);
		panelNotas.setBorder(BorderFactory.createTitledBorder("Notas del estudiante"));
		contentPane.add(panelNotas);
		panelNotas.setLayout(null);
		
		JLabel lblNota1 = new JLabel("Nota 1:");
		lblNota1.setBounds(45, 48, 46, 14);
		panelNotas.add(lblNota1);
		
		JLabel lblNota2 = new JLabel("Nota 2:");
		lblNota2.setBounds(45, 85, 46, 14);
		panelNotas.add(lblNota2);
		
		JLabel lblNota3 = new JLabel("Nota 3:");
		lblNota3.setBounds(45, 121, 46, 14);
		panelNotas.add(lblNota3);
		
		JLabel lblTps = new JLabel("TPS:");
		lblTps.setBounds(45, 156, 46, 14);
		panelNotas.add(lblTps);
		
		textFieldNota1 = new JTextField();
		textFieldNota1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg) {
				String value = textFieldNota1.getText();
				if ((arg.getKeyChar() >= '0' && arg.getKeyChar() <= '9') || arg.getKeyChar() == '.') {
					try {
						double num = Double.parseDouble(value + arg.getKeyChar());
						if (num >= 1 && num <= 10) {
							textFieldNota1.setEditable(true);
						} else {
							textFieldNota1.setEditable(false);
						}
					} catch (NumberFormatException ex) {
						textFieldNota1.setEditable(false);
					}
				} else if (arg.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					textFieldNota1.setEditable(true);
				} else {
					textFieldNota1.setEditable(false);
				}
			}
		});
		textFieldNota1.setBounds(118, 45, 174, 20);
		panelNotas.add(textFieldNota1);
		textFieldNota1.setColumns(10);
		
		textFieldNota2 = new JTextField();
		textFieldNota2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg) {
				String value = textFieldNota2.getText();
				if ((arg.getKeyChar() >= '0' && arg.getKeyChar() <= '9') || arg.getKeyChar() == '.') {
					try {
						double num = Double.parseDouble(value + arg.getKeyChar());
						if (num >= 1 && num <= 10) {
							textFieldNota2.setEditable(true);
						} else {
							textFieldNota2.setEditable(false);
						}
					} catch (NumberFormatException ex) {
						textFieldNota2.setEditable(false);
					}
				} else if (arg.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					textFieldNota2.setEditable(true);
				} else {
					textFieldNota2.setEditable(false);
				}
			}
		});
		textFieldNota2.setBounds(118, 82, 174, 20);
		panelNotas.add(textFieldNota2);
		textFieldNota2.setColumns(10);
		
		textFieldNota3 = new JTextField();
		textFieldNota3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg) {
				String value = textFieldNota3.getText();
				if ((arg.getKeyChar() >= '0' && arg.getKeyChar() <= '9') || arg.getKeyChar() == '.') {
					try {
						double num = Double.parseDouble(value + arg.getKeyChar());
						if (num >= 1 && num <= 10) {
							textFieldNota3.setEditable(true);
						} else {
							textFieldNota3.setEditable(false);
						}
					} catch (NumberFormatException ex) {
						textFieldNota3.setEditable(false);
					}
				} else if (arg.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					textFieldNota3.setEditable(true);
				} else {
					textFieldNota3.setEditable(false);
				}
			}
		});
		textFieldNota3.setBounds(118, 118, 174, 20);
		panelNotas.add(textFieldNota3);
		textFieldNota3.setColumns(10);
		
		JComboBox<String> comboBoxTPS = new JComboBox<String>();
		comboBoxTPS.setBounds(118, 153, 174, 20);
		comboBoxTPS.addItem("Aprobado");
		comboBoxTPS.addItem("Desaprobado");
		panelNotas.add(comboBoxTPS);
		
		JPanel panelPromedio = new JPanel();
		panelPromedio.setBounds(47, 256, 340, 106);
		panelPromedio.setBorder(BorderFactory.createTitledBorder("Notas del estudiante"));
		contentPane.add(panelPromedio);
		panelPromedio.setLayout(null);
		
		JLabel lblPromedio = new JLabel("Promedio:");
		lblPromedio.setBounds(45, 34, 70, 14);
		panelPromedio.add(lblPromedio);
		
		JLabel lblCondicion = new JLabel("Condicion:");
		lblCondicion.setBounds(45, 63, 70, 14);
		panelPromedio.add(lblCondicion);
		
		textFieldPromedio = new JTextField();
		textFieldPromedio.setBackground(Color.WHITE);
		textFieldPromedio.setEditable(false);
		textFieldPromedio.setBounds(116, 31, 174, 20);
		panelPromedio.add(textFieldPromedio);
		textFieldPromedio.setColumns(10);
		
		textFieldCondicion = new JTextField();
		textFieldCondicion.setBackground(Color.WHITE);
		textFieldCondicion.setEditable(false);
		textFieldCondicion.setBounds(115, 60, 175, 20);
		panelPromedio.add(textFieldCondicion);
		textFieldCondicion.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(409, 66, 112, 203);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnCalcular = new JButton("CALCULAR");
		btnCalcular.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				boolean emptyField = false;
				if (textFieldNota1.getText().isEmpty()) {
					textFieldNota1.setBackground(Color.RED);
					emptyField = true;
				} else {
					textFieldNota1.setBackground(Color.WHITE);
				}
				if (textFieldNota2.getText().isEmpty()) {
					textFieldNota2.setBackground(Color.RED);
					emptyField = true;
				} else {
					textFieldNota2.setBackground(Color.WHITE);
				}
				if (textFieldNota3.getText().isEmpty()) {
					textFieldNota3.setBackground(Color.RED);
					emptyField = true;
				} else {
					textFieldNota3.setBackground(Color.WHITE);
				}
				
				
				
				if (!emptyField) {
					textFieldNota1.setEditable(false);
					textFieldNota2.setEditable(false);
					textFieldNota3.setEditable(false);
					double nota1 = Double.valueOf(textFieldNota1.getText());
					double nota2 = Double.valueOf(textFieldNota2.getText());
					double nota3 = Double.valueOf(textFieldNota3.getText());
					
					//Valido si alguna de las notas es menor a 6
					boolean TpDesaprobado = false;
					if(nota1 < 6 || nota2 < 6 || nota3 < 6) {
						TpDesaprobado = true;
					}
					
					Double prom = (nota1 + nota2 + nota3) / 3;
					textFieldPromedio.setText(String.valueOf(prom));
					
					if (comboBoxTPS.getSelectedItem() == "Desaprobado" || prom < 6 || TpDesaprobado == true) {
						textFieldCondicion.setText("Libre");
					} else if (prom >= 8 && comboBoxTPS.getSelectedItem() == "Aprobado" ) {
						textFieldCondicion.setText("Promocionado");
					} else {
						textFieldCondicion.setText("Regular");
					}
				}
			}
		});
		btnCalcular.setBounds(0, 22, 112, 37);
		panel.add(btnCalcular);
		
		JButton btnNuevo = new JButton("NUEVO");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldNota1.setText("");
				textFieldNota2.setText("");
				textFieldNota3.setText("");
				textFieldPromedio.setText("");
				textFieldCondicion.setText("");
				textFieldNota1.setEditable(true);
				textFieldNota2.setEditable(true);
				textFieldNota3.setEditable(true);
				
			}
		});
		btnNuevo.setBounds(0, 62, 112, 37);
		panel.add(btnNuevo);
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnSalir.setBounds(0, 103, 112, 37);
		panel.add(btnSalir);
	}
}
