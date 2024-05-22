package tfg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DisposicionGrupal {

	private List<Grupo> listaGrupos;

	public DisposicionGrupal(List<Grupo> listaGrupos) {
		super();
		this.listaGrupos = listaGrupos;
	}

	public DisposicionGrupal() {
		super();
		this.listaGrupos = new ArrayList<Grupo>();
	}

	public List<Grupo> getListaGrupos() {
		return listaGrupos;
	}

	public void setListaGrupos(List<Grupo> listaPersonas) {
		this.listaGrupos = listaPersonas;
	}

	public double calculateOverallMean() {
		if (listaGrupos.isEmpty()) {
			return 0.0;
		}
		double sum = 0;
		for (Grupo grupo : listaGrupos) {
			sum += grupo.calcularMedia();
		}

		return (double) sum / listaGrupos.size();
	}

	public double calculateRange() {
		if (listaGrupos.isEmpty()) {
			return 0.0;
		}
		double min = Collections.min(getMedias());
		double max = Collections.max(getMedias());
		return max - min;
	}

	public List<Double> getMedias() {
		List<Double> lMedias = new ArrayList<Double>();
		for (Grupo grupo : listaGrupos) {
			lMedias.add(grupo.calcularMedia());
		}
		return lMedias;
	}

	public void addGrupo(Grupo grupo) {
		listaGrupos.add(new Grupo(grupo.getListaPersonas()));
	}

	public Grupo getGrupo(int i) {
		return listaGrupos.get(i);
	}

	public String toString() {
		String string = "[";
		for (Grupo grupo : listaGrupos) {
			string = string + grupo.toString() + "\n";
		}
		string = string + "]\n";
		return string + "|| MM_t=" + calculateOverallMean() + ",R=" + calculateRange();
	}

}
