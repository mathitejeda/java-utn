package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import entidad.Persona;

import javax.swing.JLabel;
import javax.swing.JList;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ModificarPersona extends JPanel{
	private JTextField txtDNI;
	private JTextField txtApellido;
	private JTextField txtNombre;
	private JButton btnModificar;
	private JList<Persona> list;
	private DefaultListModel<Persona> modelPersona;
	
	public ModificarPersona() {

		super();
		initialize();
	}
	
	private void initialize() 
	{	
		setLayout(null);
		
		JLabel lblSeleccion = new JLabel("Seleccione la persona que desea modificar:");
		lblSeleccion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSeleccion.setBounds(30, 17, 393, 14);
		add(lblSeleccion);
		
		modelPersona = new DefaultListModel<Persona>();
		list = new JList<>(modelPersona);
		list.setBounds(30, 31, 393, 118);
		add(list);
		
		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		txtDNI.setBounds(307, 160, 116, 20);
		add(txtDNI);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(171, 160, 116, 20);
		add(txtApellido);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(30, 160, 116, 20);
		add(txtNombre);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(264, 191, 132, 23);
		add(btnModificar);	
	}
	
	public JList<Persona> getList() {
		return list;
	}

	public void setList(JList<Persona> list) {
		this.list = list;
	}
	
	public DefaultListModel<Persona> getModelPersona() {
		return modelPersona;
	}
	
	public void setModelPersona(DefaultListModel<Persona> modelPersona) {
		this.modelPersona = modelPersona;
	}
	
	public void llenarLista (List<Persona> personas) {
		this.getModelPersona().clear();
		for (Persona p : personas) {
			this.getModelPersona().addElement(p);
		}
		list.setModel(modelPersona);
	}

	public JTextField getTxtDNI() {
		return txtDNI;
	}

	public void setTxtDNI(JTextField txtDNI) {
		this.txtDNI = txtDNI;
	}

	public JTextField getTxtApellido() {
		return txtApellido;
	}

	public void setTxtApellido(JTextField txtApellido) {
		this.txtApellido = txtApellido;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(JButton btnModificar) {
		this.btnModificar = btnModificar;
	}
	
}
