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

    public void guardarParticipantesEnGrupos(List<Grupo> grupos) {
        Distribucion.crearArchivoXML(grupos, this);
    }
}

