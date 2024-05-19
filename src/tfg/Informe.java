package tfg;

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
        return "Informe [nombre=" + nombre + ", valor=" + valor + "]";
    }
}
