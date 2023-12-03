package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class Ventana3 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private ButtonGroup bg = new ButtonGroup();

	public Ventana3() {
		setTitle("Seleccion multiple");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 449, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelSistemaOperativo = new JPanel();
		panelSistemaOperativo.setBounds(26, 11, 379, 41);
		panelSistemaOperativo.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panelSistemaOperativo);
		panelSistemaOperativo.setLayout(null);
		
		JLabel lblIngreseUnSistema = new JLabel("Elige un sistema operativo");
		lblIngreseUnSistema.setBounds(10, 13, 156, 14);
		panelSistemaOperativo.add(lblIngreseUnSistema);
		
		JRadioButton rdbtnWindows = new JRadioButton("Windows");
		rdbtnWindows.setBounds(167, 9, 80, 23);
		panelSistemaOperativo.add(rdbtnWindows);
		
		JRadioButton rdbtnMac = new JRadioButton("Mac");
		rdbtnMac.setBounds(249, 9, 60, 23);
		panelSistemaOperativo.add(rdbtnMac);
		
		JRadioButton rdbtnLinux = new JRadioButton("Linux");
		rdbtnLinux.setBounds(307, 9, 66, 23);
		panelSistemaOperativo.add(rdbtnLinux);
		
		bg.add(rdbtnWindows);
		bg.add(rdbtnMac);
		bg.add(rdbtnLinux);
		
		JPanel panelEspecialidad = new JPanel();
		panelEspecialidad.setBounds(26, 73, 379, 92);
		panelEspecialidad.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panelEspecialidad);
		panelEspecialidad.setLayout(null);
		
		JCheckBox chckbxProgra = new JCheckBox("Programaci\u00F3n");
		chckbxProgra.setBounds(196, 7, 111, 23);
		panelEspecialidad.add(chckbxProgra);
		
		JLabel lblNewLabel = new JLabel("Elige una especialidad");
		lblNewLabel.setBounds(22, 37, 168, 14);
		panelEspecialidad.add(lblNewLabel);
		
		JCheckBox chckbxAdmin = new JCheckBox("Administraci\u00F3n");
		chckbxAdmin.setBounds(196, 33, 111, 23);
		panelEspecialidad.add(chckbxAdmin);
		
		JCheckBox chckbxDisenio = new JCheckBox("Dise\u00F1o gr\u00E1fico");
		chckbxDisenio.setBounds(196, 59, 111, 23);
		panelEspecialidad.add(chckbxDisenio);
		
		JLabel lblCantidadDeHoras = new JLabel("Cantidad de horas en el computador:");
		lblCantidadDeHoras.setBounds(47, 187, 185, 14);
		contentPane.add(lblCantidadDeHoras);
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setBounds(252, 184, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String so = "";
				String spc = "";
				
					if (rdbtnWindows.isSelected()) {
						so = rdbtnWindows.getText();
					} else if (rdbtnMac.isSelected()) {
						so = rdbtnMac.getText();
					} else if (rdbtnLinux.isSelected()){
						so = rdbtnLinux.getText();
					}				
				
				if(rdbtnWindows.isSelected()==false && rdbtnMac.isSelected()==false && rdbtnLinux.isSelected()==false) {
					JOptionPane.showMessageDialog(null, "Seleccionar un SO");
				} 
				else if(chckbxProgra.isSelected()==false && chckbxAdmin.isSelected()==false && chckbxDisenio.isSelected()==false) {
					JOptionPane.showMessageDialog(null, "Seleccionar una/s especialidad/es");
				}
				else if(textField.getText().isEmpty() || !isNumeric(textField.getText())) {
					JOptionPane.showMessageDialog(null, "Ingresar cantidad de horas válida");
				} 
				else  {
					if(chckbxProgra.isSelected()) {
						spc += " - " + chckbxProgra.getText();
					} 
					if(chckbxAdmin.isSelected()) {
						spc += " - " + chckbxAdmin.getText();
					}
					if(chckbxDisenio.isSelected()) {
						spc += " - " + chckbxDisenio.getText();
					}
					
				JOptionPane.showMessageDialog(null, so + spc + " - " + textField.getText()+"Hs.");
				}
			}
		});
		btnAceptar.setBounds(307, 215, 89, 23);
		contentPane.add(btnAceptar);
	}
	
	public static boolean isNumeric(String str)	{
	      NumberFormat formatter = NumberFormat.getInstance();
	      ParsePosition pos = new ParsePosition(0);
	      formatter.parse(str, pos);
	      return str.length() == pos.getIndex();
	}
}					

