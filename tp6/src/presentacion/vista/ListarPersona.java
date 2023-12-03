package presentacion.vista;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entidad.Persona;

public class ListarPersona extends JPanel{

	private JTable tablaPersonas;
	
	private DefaultTableModel modelPersonas;
	private String[] nombreColumnas = {"Nombre","Apellido", "DNI"};
	
	 public ListarPersona() {
			
		super();
		initialize();
	}
	 
		private void initialize() 
		{
			
			this.setLayout(null);
			
			JPanel panel = new JPanel();
			panel.setBounds(15, 31, 444, 227);
			this.add(panel);
			panel.setLayout(null);
			
			JScrollPane spPersonas = new JScrollPane();
			spPersonas.setBounds(15, 30, 383, 126);
			panel.add(spPersonas);
			
			modelPersonas = new DefaultTableModel(null,nombreColumnas);
			tablaPersonas = new JTable(modelPersonas);
			
			tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(103);
			tablaPersonas.getColumnModel().getColumn(0).setResizable(false);
			tablaPersonas.getColumnModel().getColumn(1).setPreferredWidth(100);
			tablaPersonas.getColumnModel().getColumn(1).setResizable(false);
			tablaPersonas.getColumnModel().getColumn(2).setPreferredWidth(100);
			tablaPersonas.getColumnModel().getColumn(2).setResizable(false);
			
			spPersonas.setViewportView(tablaPersonas);
			/*
			JPanel panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setBounds(15, 37, 438, 118);
			this.add(panel_1); */
			
		}
		
		public void show()
		{
			this.setVisible(true);
		}
		
		public DefaultTableModel getModelPersonas() 
		{
			return modelPersonas;
		}
		
		public JTable getTablaPersonas()
		{
			return tablaPersonas;
		}
		
		public String[] getNombreColumnas() 
		{
			return nombreColumnas;
		}
		
		public void llenarTabla(List<Persona> personasEnTabla) {
			this.getModelPersonas().setRowCount(0);
			this.getModelPersonas().setColumnCount(0);
			this.getModelPersonas().setColumnIdentifiers(this.getNombreColumnas());

			for (Persona p : personasEnTabla)
			{
				String nombre = p.getNombre();
				String apellido = p.getApellido();
				String dni = p.getDni();
				Object[] fila = {nombre, apellido, dni};
				this.getModelPersonas().addRow(fila);
			}
			
		}
		
}
