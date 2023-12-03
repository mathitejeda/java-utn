package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana1 extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Nombre;
	private JTextField textField_Apellido;
	private JTextField textField_Telefono;
	private JTextField textField_FechaNac;
	private JLabel lblApellido;
	private JLabel lblTelfono;
	private JLabel lblFechaNac;
	private JLabel lblLosDatosIngresados;
	
	public Ventana1() {
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("Contactos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 425, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textField_Nombre = new JTextField();
		textField_Nombre.setColumns(10);
		
		textField_Apellido = new JTextField();
		textField_Apellido.setColumns(10);
		
		textField_Telefono = new JTextField();
		textField_Telefono.setColumns(10);
		
		textField_FechaNac = new JTextField();
		textField_FechaNac.setColumns(10);		
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		lblFechaNac = new JLabel("Fecha Nac.");
		lblFechaNac.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(textField_Nombre.getText().isEmpty()) {
					setColorTxtF(textField_Nombre,Color.RED);
				}
				if(textField_Apellido.getText().isEmpty()) {
					setColorTxtF(textField_Apellido,Color.RED);
				}
				if(textField_Telefono.getText().isEmpty()) {
					setColorTxtF(textField_Telefono,Color.RED);
				}
				if(textField_FechaNac.getText().isEmpty()) {
					setColorTxtF(textField_FechaNac,Color.RED);
				}
			
				if (!textField_Nombre.getText().isEmpty() && !textField_Apellido.getText().isEmpty() && !textField_Telefono.getText().isEmpty() && !textField_FechaNac.getText().isEmpty()) {
				
				lblLosDatosIngresados.setText("Los datos ingresados fueron: " + textField_Nombre.getText() + "-" + textField_Apellido.getText() + "-" + textField_Telefono.getText() + "-" + textField_FechaNac.getText());

				setColorTxtF(textField_Nombre,Color.white);
				setColorTxtF(textField_Apellido,Color.white);
				setColorTxtF(textField_Telefono,Color.white);
				setColorTxtF(textField_FechaNac,Color.white);
				
				textField_Nombre.setText("");
				textField_Apellido.setText("");
				textField_Telefono.setText("");
				textField_FechaNac.setText("");
				}
			}
		});
				
		lblLosDatosIngresados = new JLabel("Los datos ingresados fueron: ");
		lblLosDatosIngresados.setFont(new Font("Tahoma", Font.BOLD, 11));
		
	
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(55)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblFechaNac, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblTelfono)
								.addPreferredGap(ComponentPlacement.RELATED))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblApellido, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))))
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_Apellido, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_Nombre, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_Telefono, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_FechaNac, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(48, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(38)
							.addComponent(lblLosDatosIngresados))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(261, Short.MAX_VALUE)
							.addComponent(btnMostrar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
					.addGap(38))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(textField_Nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblApellido, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_Apellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelfono, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_Telefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFechaNac, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_FechaNac, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnMostrar, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addComponent(lblLosDatosIngresados)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void setColorTxtF(JTextField TxtField, Color color)
	{
		TxtField.setBackground(color);
	}
	
}

