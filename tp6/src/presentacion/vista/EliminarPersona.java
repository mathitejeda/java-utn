package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import entidad.Persona;

public class EliminarPersona extends JPanel {

	private JLabel lblEliminarUsuarios;
	private JList<Persona> list;

	private JButton btnEliminar;
	private DefaultListModel<Persona> modelPersona;

	public EliminarPersona() {
		setLayout(null);
		
		lblEliminarUsuarios = new JLabel("Eliminar usuarios");
		lblEliminarUsuarios.setBounds(168, 11, 104, 20);
		add(lblEliminarUsuarios);
		
		modelPersona = new DefaultListModel<Persona>();
		list = new JList<>(modelPersona);
		list.setBounds(110, 31, 218, 165);
		add(list);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(167, 207, 115, 29);
		add(btnEliminar);
	}

	public JLabel getLblEliminarUsuarios() {
		return lblEliminarUsuarios;
	}

	public void setLblEliminarUsuarios(JLabel lblEliminarUsuarios) {
		this.lblEliminarUsuarios = lblEliminarUsuarios;
	}

	public JList<Persona> getList() {
		return list;
	}

	public void setList(JList<Persona> list) {
		this.list = list;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public DefaultListModel<Persona> getModelPersona() {
		return modelPersona;
	}

	public void setModelPersona(DefaultListModel<Persona> modelPersona) {
		this.modelPersona = modelPersona;
	}
	
	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}
	
	public void llenarLista (List<Persona> personas) {
		this.getModelPersona().clear();
		for (Persona p : personas) {
			this.getModelPersona().addElement(p);
		}
		list.setModel(modelPersona);
	}
}
