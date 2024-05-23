package tfg;

import java.util.Collections;
import java.util.List;

public class Persona {

	private final int id;
	private final String nombre;
	private final List<Integer> listaTensiones;

	public Persona(int id, String nombre, List<Integer> listaTensiones) {
		this.id = id;
		this.nombre = nombre;
		this.listaTensiones = listaTensiones;
	}

	public double calcularTension(List<Persona> personasMesa) {
		if (personasMesa == null || personasMesa.size() <= 1) {
			return 0.0;
		}

		int tensionConMesa = 0;
		int personasMesaSizeMinusOne = personasMesa.size() - 1;

		for (Persona persona : personasMesa) {
			if (persona != null && persona.id < listaTensiones.size()) {
				tensionConMesa += listaTensiones.get(persona.id);
			}
		}

		return (double) tensionConMesa / personasMesaSizeMinusOne;
	}

	@Override
	public String toString() {
		return nombre;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public List<Integer> getListaTensiones() {
		return Collections.unmodifiableList(listaTensiones);
	}
}
