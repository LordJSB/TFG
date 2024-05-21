package tfg;

import java.util.ArrayList;
import java.util.List;

public class Persona {
	
	private int id;
	
	private String nombre;
	
	private List<Integer> listaTensiones= new ArrayList<Integer> ();

	public Persona(int id,String nombre, List<Integer> listaTensiones) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.listaTensiones = listaTensiones;
	}
	
	public double calcularTension(List<Persona> personasMesa) {
		int tensionConMesa=0;
		for(Persona persona:personasMesa) {
			tensionConMesa+=listaTensiones.get(persona.id);
		}
		
		
		if(personasMesa.size()<=1) {
			return (double)0;
		}
		return (double)tensionConMesa/(personasMesa.size()-1);
		
	}
	public String toString() {
		return nombre;
		
	}
	

}
