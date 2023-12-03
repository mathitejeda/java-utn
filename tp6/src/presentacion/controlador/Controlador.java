package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;

import dao.PersonaDao;
import daoImpl.PersonaDaoImpl;
import entidad.Persona;
import negocio.PersonaNegocio;
import presentacion.vista.AgregarPersona;
import presentacion.vista.EliminarPersona;
import presentacion.vista.ListarPersona;
import presentacion.vista.ModificarPersona;
import presentacion.vista.VentanaPrincipal;

public class Controlador implements ActionListener{
	private VentanaPrincipal ventanaPrincipal;
	private AgregarPersona pnlAgregarPersona;
	private EliminarPersona pnEliminarPersona;
	private ModificarPersona pnlModificarPersona;
	private ListarPersona pnlListarPersona;
	private PersonaNegocio pNeg;
	private ArrayList<Persona> personasEnTabla;
		
	public Controlador (VentanaPrincipal vista, PersonaNegocio pNeg) {
		this.ventanaPrincipal = vista;
		this.pNeg = pNeg;
		
		this.pnlAgregarPersona = new AgregarPersona();
		this.pnEliminarPersona = new EliminarPersona();
		this.pnlModificarPersona = new ModificarPersona();
		this.pnlListarPersona = new ListarPersona();
		
		this.ventanaPrincipal.getMntmAgregar().addActionListener(a->EventoClickMenu_AbrirPanel_AgregarPersona(a));
		this.ventanaPrincipal.getMntmEliminar().addActionListener(a->EventoClickMenu_AbrirPanel_EliminarPersona(a));
		this.ventanaPrincipal.getMntmModificar().addActionListener(a->EventoClickMenu_AbrirPanel_ModificarPersona(a));
		this.ventanaPrincipal.getMntmListar().addActionListener(a->EventoClickMenu_ListarPanel_ListarPersona(a));
		
		validarCampos();
		
		this.pnlAgregarPersona.getBtnAceptar().addActionListener(a->EventoClickBoton_AgregarPesona_PanelAgregarPersonas(a));
		this.pnEliminarPersona.getBtnEliminar().addActionListener(a->EventoClickBoton_EliminarPersona_PanelEliminarPersona(a));
		this.pnlModificarPersona.getList().addListSelectionListener(a->EventoListSelection_Copiar_Datos(a));
		this.pnlModificarPersona.getBtnModificar().addActionListener(a->EventoClickBoton_ModificarPersona_PanelModificarPersonas(a));
		
	}
	
	public void  EventoClickMenu_ListarPanel_ListarPersona(ActionEvent a)
	{		
		ventanaPrincipal.getContentPane().removeAll();
		ventanaPrincipal.getContentPane().add(pnlListarPersona);
		ventanaPrincipal.getContentPane().repaint();
		ventanaPrincipal.getContentPane().revalidate();
		this.refrescarTabla();
	}
	
	
	public void  EventoClickMenu_AbrirPanel_AgregarPersona(ActionEvent a)
	{		
		ventanaPrincipal.getContentPane().removeAll();
		ventanaPrincipal.getContentPane().add(pnlAgregarPersona);
		ventanaPrincipal.getContentPane().repaint();
		ventanaPrincipal.getContentPane().revalidate();
	}
	
	public void EventoClickMenu_AbrirPanel_EliminarPersona(ActionEvent a) {
		ventanaPrincipal.getContentPane().removeAll();
		ventanaPrincipal.getContentPane().add(pnEliminarPersona);
		ventanaPrincipal.getContentPane().repaint();
		ventanaPrincipal.getContentPane().revalidate();
		this.refrescarTabla();
	}
	
	
	public void EventoClickMenu_AbrirPanel_ModificarPersona(ActionEvent a) {
		ventanaPrincipal.getContentPane().removeAll();
		ventanaPrincipal.getContentPane().add(pnlModificarPersona);
		ventanaPrincipal.getContentPane().repaint();
		ventanaPrincipal.getContentPane().revalidate();
		this.refrescarTabla();
	}
	
	public void validarCampos() {
		
		//validaciones campos pnlAgregar
		this.pnlAgregarPersona.getTxtNombre().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			    int key = e.getKeyChar();

			    boolean mayusculas = key >= 65 && key <= 90;
			    boolean minusculas = key >= 97 && key <= 122;
			    boolean espacio = key == 32;
			            
			     if (!(minusculas || mayusculas || espacio))
			    {
			        e.consume();
			    }
			}
		});
		
		this.pnlAgregarPersona.getTxtApellido().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			    int key = e.getKeyChar();

			    boolean mayusculas = key >= 65 && key <= 90;
			    boolean minusculas = key >= 97 && key <= 122;
			    boolean espacio = key == 32;
			            
			     if (!(minusculas || mayusculas || espacio))
			    {
			        e.consume();
			    }
			}
		});
		
				this.pnlAgregarPersona.getTxtDni().addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						int key = e.getKeyChar();

					    boolean numeros = key >= 48 && key <= 57;
					        
					    if (!numeros)
					    {
					        e.consume();
					    }
					}
				});
					
				//validaciones campos pnlModificar
				this.pnlModificarPersona.getTxtNombre().addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
					    int key = e.getKeyChar();

					    boolean mayusculas = key >= 65 && key <= 90;
					    boolean minusculas = key >= 97 && key <= 122;
					    boolean espacio = key == 32;
					            
					     if (!(minusculas || mayusculas || espacio))
					    {
					        e.consume();
					    }
					}
				});
				
				this.pnlModificarPersona.getTxtApellido().addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
					    int key = e.getKeyChar();

					    boolean mayusculas = key >= 65 && key <= 90;
					    boolean minusculas = key >= 97 && key <= 122;
					    boolean espacio = key == 32;
					            
					     if (!(minusculas || mayusculas || espacio))
					    {
					        e.consume();
					    }
					}
				});
				
				//this.pnlModificarPersona.getTxtDNI().setEditable(false);			
	}
	
	
	public void inicializar () {
		this.ventanaPrincipal.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	private void EventoClickBoton_ModificarPersona_PanelModificarPersonas(ActionEvent a) {	

			String dni = this.pnlModificarPersona.getTxtDNI().getText();
			String nombre = this.pnlModificarPersona.getTxtNombre().getText();
			String apellido = this.pnlModificarPersona.getTxtApellido().getText();
			Persona PersonaEditada = new Persona(dni, nombre, apellido);
			
			Persona persona_a_editar = (Persona) this.pnlModificarPersona.getList().getSelectedValue();
			
			
			if(persona_a_editar != null)
			{
				boolean estado = pNeg.update(PersonaEditada);
				if(estado==true)
				{
					JOptionPane.showMessageDialog(null, "Persona editada con exito");
					this.refrescarTabla();
				}
				else
					JOptionPane.showMessageDialog(null, "Persona no editada");
			}
			else
				JOptionPane.showMessageDialog(null, "Seleccione una persona");
			
			this.pnlModificarPersona.getTxtDNI().setText("");
			this.pnlModificarPersona.getTxtNombre().setText("");
			this.pnlModificarPersona.getTxtApellido().setText("");	
			this.pnlModificarPersona.getList().clearSelection(); //Limpia la seleccion de la lista
	}	
	
	private void EventoClickBoton_AgregarPesona_PanelAgregarPersonas(ActionEvent a) {
		
		String dni = this.pnlAgregarPersona.getTxtDni().getText();
		String nombre = this.pnlAgregarPersona.getTxtNombre().getText();
		String apellido = this.pnlAgregarPersona.getTxtApellido().getText();
		Persona nuevaPersona = new Persona(dni, nombre, apellido);
		if (!dni.equals("") && !nombre.equals("") && !apellido.equals("")) {
			//Valido que el no exista en la BD
			if(pNeg.readDni(dni)== null) {
				boolean estado = pNeg.insert(nuevaPersona);	
				if(estado==true)	
				{
					JOptionPane.showMessageDialog(null, "Persona agregada con exito");
					this.pnlAgregarPersona.getTxtDni().setText("");
					this.pnlAgregarPersona.getTxtNombre().setText("");	
					this.pnlAgregarPersona.getTxtApellido().setText("");	
				}else	
					JOptionPane.showMessageDialog(null, "Persona no agregada");	
			}else	
				JOptionPane.showMessageDialog(null, "El Dni ya se encuentra registrado");	
		}else 
			JOptionPane.showMessageDialog(null, "Completar todos los campos");
	}
	
	
	private void EventoClickBoton_EliminarPersona_PanelEliminarPersona(ActionEvent a) {
		Persona persona_a_eliminar = (Persona) this.pnEliminarPersona.getList().getSelectedValue();
		if(persona_a_eliminar != null)
		{
			boolean estado = pNeg.delete(persona_a_eliminar);
			if(estado==true)
			{
				JOptionPane.showMessageDialog(null, "Persona eliminada con exito");
				this.refrescarTabla();
			}
			else
				JOptionPane.showMessageDialog(null, "Persona no eliminada");
		}
		else
			JOptionPane.showMessageDialog(null, "Seleccione una persona");
	}

	
	private void EventoListSelection_Copiar_Datos(ListSelectionEvent a) {	
			Persona persona_seleccionada = (Persona) this.pnlModificarPersona.getList().getSelectedValue();
			if(persona_seleccionada != null) {				
				
				pnlModificarPersona.getTxtNombre().setText(persona_seleccionada.getNombre().toString());
				pnlModificarPersona.getTxtApellido().setText(persona_seleccionada.getApellido().toString());
				pnlModificarPersona.getTxtDNI().setText(persona_seleccionada.getDni().toString());
			}
	}
	
	
	private void refrescarTabla()
	{
		this.personasEnTabla = (ArrayList<Persona>) pNeg.readAll();
		this.pnEliminarPersona.llenarLista(this.personasEnTabla);
		this.pnlModificarPersona.llenarLista(this.personasEnTabla);
		this.pnlListarPersona.llenarTabla(this.personasEnTabla);
		
	}
}
