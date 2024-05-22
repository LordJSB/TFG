package tfg;

import java.util.List;

public class Informe {
	private String nombre;
	private int valor;

	public Informe(String nombre, int valor) {
		this.nombre = nombre;
		this.valor = valor;
	}

	public String getNombre() {
		return nombre;
	}

	public int getValor() {
		return valor;
	}

	@Override
	public String toString() {
		return "[Usuario=" + nombre + ", número de participantes=" + valor + "]";
	}

	public void guardarParticipantesEnGrupos(List<Grupo> grupos) {
		StringBuilder xmlBuilder = new StringBuilder();
		xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		xmlBuilder.append("<informe>\n");
		xmlBuilder.append("  <autor>").append(nombre).append("</autor>\n");
		xmlBuilder.append("  <participantes>").append(valor).append("</participantes>\n");
		xmlBuilder.append("  <grupos>\n");

		for (Grupo grupo : grupos) {
			xmlBuilder.append("    <grupo>\n");
			for (Persona persona : grupo.getListaPersonas()) {
				xmlBuilder.append("      <participante>\n");
				xmlBuilder.append("        <nombre>").append(persona.getNombre()).append("</nombre>\n");
				xmlBuilder.append("      </participante>\n");
			}
			xmlBuilder.append("    </grupo>\n");
		}

		xmlBuilder.append("  </grupos>\n");
		xmlBuilder.append("</informe>");

		// Aquí puedes guardar xmlBuilder.toString() en un archivo o realizar otrasoperaciones con él
		System.out.println(xmlBuilder.toString());
	}
}
