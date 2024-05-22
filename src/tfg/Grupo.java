package tfg;

import java.util.ArrayList;
import java.util.List;

public class Grupo {

	private List<Persona> listaPersonas;

	public Grupo(List<Persona> listaPersonas) {
		this.listaPersonas = new ArrayList<>(listaPersonas);
	}

	public Grupo() {
		this.listaPersonas = new ArrayList<>();
	}

	public Double calcularMedia() {
		double sum = 0;
		for (Persona persona : listaPersonas) {
			sum += persona.calcularTension(listaPersonas);
		}
		return listaPersonas.isEmpty() ? 0 : sum / listaPersonas.size();
	}

	public List<Persona> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(List<Persona> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

	public void addPersona(Persona persona) {
		listaPersonas.add(persona);
	}

	public Persona getPersona(int i) {
		return listaPersonas.get(i);
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder("[");
		for (Persona persona : listaPersonas) {
			string.append(persona.toString()).append("- T=").append(persona.calcularTension(listaPersonas))
					.append(", ");
		}
		if (!listaPersonas.isEmpty()) {
			string.setLength(string.length() - 2); // Quita comas y espacios
		}
		string.append("]");
		return string + "| M_t=" + calcularMedia();
	}
}
