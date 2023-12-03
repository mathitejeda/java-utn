package TP5;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelIngresoPeliculas extends JPanel {

	private JTextField textFieldNombre;
	private JComboBox<Categoria> comboBoxGenero;
	private JButton btnAceptar;
	private JLabel lblId;
	private JLabel lblNro;
	private JLabel lblNombre;
	private JLabel lblGenero;
	private DefaultListModel<Pelicula> listModel;
	
	
	public PanelIngresoPeliculas() {
		setLayout(null);
		
		lblId = new JLabel("ID");
		lblId.setBounds(33, 11, 46, 14);
		add(lblId);
		
		lblNro = new JLabel("Nro");
		lblNro.setText(String.valueOf(Principal.listModel.getSize() + 1));
		lblNro.setBounds(146, 11, 46, 14);
		add(lblNro);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(33, 71, 46, 14);
		add(lblNombre);
		
		lblGenero = new JLabel("Genero");
		lblGenero.setBounds(33, 134, 46, 14);
		add(lblGenero);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(146, 68, 86, 20);
		add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		comboBoxGenero = new JComboBox<Categoria>();
		comboBoxGenero.setBounds(146, 131, 86, 20);
		add(comboBoxGenero);
		
		comboBoxGenero.addItem(new Categoria("Seleccione un genero"));
		comboBoxGenero.addItem(new Categoria("Terror"));
		comboBoxGenero.addItem(new Categoria("Accion"));
		comboBoxGenero.addItem(new Categoria("Suspenso"));
		comboBoxGenero.addItem(new Categoria("Romantica"));
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textFieldNombre.getText().isEmpty() && comboBoxGenero.getSelectedIndex()!=0) {
					Pelicula pl = new Pelicula();
					pl.setNombre(textFieldNombre.getText());
					pl.setGenero((Categoria)comboBoxGenero.getSelectedItem());
					listModel.addElement(pl);
					
					JOptionPane.showMessageDialog(null, "Pelicula cargada exitosamente");
					
					textFieldNombre.setText("");
					comboBoxGenero.setSelectedIndex(0);
					lblNro.setText(String.valueOf(Principal.listModel.getSize() + 1));
				}else {
					JOptionPane.showMessageDialog(null, "Debe ingresar un nombre para la pelicula y seleccionar una categoria");
				}
			}
		});
		btnAceptar.setBounds(33, 185, 89, 23);
		add(btnAceptar);			
		
	}
	
	public void setDefaultListModel(DefaultListModel<Pelicula> listModelRecibido)
	{
		this.listModel = listModelRecibido;
	}
}
