package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AgregarPersona extends JPanel {

	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;

	public AgregarPersona() {
		super();
		initialize();
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtApellido() {
		return txtApellido;
	}

	public void setTxtApellido(JTextField txtApellido) {
		this.txtApellido = txtApellido;
	}

	public JTextField getTxtDni() {
		return txtDni;
	}

	public void setTxtDni(JTextField txtDni) {
		this.txtDni = txtDni;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(JButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}
	
	private JButton btnAceptar;
	
	private void initialize() 
	{
		
		setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(28, 11, 69, 20);
		add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(28, 55, 69, 20);
		add(lblApellido);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(28, 96, 69, 20);
		add(lblDni);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		txtNombre.setBounds(136, 8, 146, 26);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(136, 52, 146, 26);
		add(txtApellido);
		txtApellido.setColumns(10);
		
		txtDni = new JTextField();
		txtDni.setBounds(136, 93, 146, 26);
		add(txtDni);
		txtDni.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(28, 140, 146, 29);
		add(btnAceptar);
		
	}
	
	public void show()
	{
		this.setVisible(true);
	}
	
}
