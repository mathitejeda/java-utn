package negocioImpl;

import java.util.List;

import javax.swing.JOptionPane;

import dao.PersonaDao;
import daoImpl.PersonaDaoImpl;
import entidad.Persona;
import negocio.PersonaNegocio;

public class PersonaNegocioImpl implements PersonaNegocio{

	PersonaDao pdao = new PersonaDaoImpl();
	
	@Override
	public boolean insert(Persona persona) {
		
		boolean estado=false;
		if(persona.getNombre().trim().length()>0 && persona.getApellido().trim().length()>0 && persona.getDni().trim().length()>0)
		{
			estado=pdao.insert(persona);
		}
		return estado;
	}

	@Override
	public boolean delete(Persona persona_a_eliminar) {
		boolean estado = false;
		if (persona_a_eliminar.getDni().length() > 0) {
			estado = pdao.delete(persona_a_eliminar);
		}
		return estado;
	}

	@Override
	public List<Persona> readAll() {
		return pdao.readAll();
	}

	@Override
	public boolean update(Persona persona_a_editar) {
		boolean estado = false;
		if (persona_a_editar.getNombre().length() > 0 && persona_a_editar.getApellido().length() > 0) {
			estado = pdao.update(persona_a_editar);
		}
		return estado;
	}

	@Override
	public Persona readDni(String dni) {
		Persona personaBuscada = new Persona();
		if(dni.trim().length()>0) {
			personaBuscada = pdao.ReadDni(dni);
		}else {
			personaBuscada = null;
		}
		return personaBuscada;
	}
}
