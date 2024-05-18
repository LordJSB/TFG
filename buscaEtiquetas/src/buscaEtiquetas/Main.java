package buscaEtiquetas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) throws IOException {
		menuImpresion();
	}

	public static void menuImpresion() throws IOException {
		File archivoJSON = leeArchivoJSON();
		File archivoXML = leeArchivoXML();
		try (Scanner sc = new Scanner(System.in)) {
			boolean bucle = true;
			System.out.print("\nEscoja entre las distintas opciones: " + "\n1: Leer un XML e imprimir un XML"
					+ "\n2: Leer un XML e imprimir un JSON" + "\n3: Leer un JSON e imprimir un XML"
					+ "\n4: Leer un JSON e imprimir un JSON" + "\n0: Salir"
					+ "\nInserte un número de los establecidos anteriormente: ");
			while (bucle) {
				switch (sc.nextLine().toString()) {
				case "0":
					System.out.println("Gracias por participar");
					bucle = false;
					break;
				case "1":
					deXMLaXML(archivoXML);
					break;
				case "2":
					deXMLaJSON(archivoXML);
					break;
				case "3":
					deJSONaXML(archivoJSON);
					break;
				case "4":
					deJSONaJSON(archivoJSON);
					break;
				default:
					System.out.println(
							"ERROR: Escoja un número entre el 0 y el 4 acorde a las opciones anteriormente listadas");
					break;
				}
			}
		}
	}

	public static void menuReserva(File archivo) throws IOException {
		try (Scanner sc = new Scanner(System.in)) {
			boolean bucle = true;
			System.out.print("\nEscoja entre los siguientes campos para ver los datos de la reserva: "
					+ "\n1: Nombre y apellidos" + "\n2: Fecha del evento" + "\n3: Tipo de evento"
					+ "\n4: Asistentes confirmados al evento" + "\n5: Tipo de cocina en el evento"
					+ "\n6: Número de jornadas que durará el evento" + "\n7: Cantidad de habitaciones disponibles"
					+ "\n8: Tipos de mesa durante las comidas" + "\n9: Cantidad de comensales por mesa" + "\n0: Salir"
					+ "\nInserte un número de los establecidos anteriormente: ");
			while (bucle) {
				switch (sc.nextLine().toString()) {
				case "0":
					System.out.println("Gracias por participar");
					bucle = false;
					break;
				case "1":
					String nombreReserva = buscaDatos(archivo, "nombre");
					System.out.println("Nombre: " + nombreReserva);
					System.out.print("\nInserte un número de los establecidos anteriormente: ");
					break;
				case "2":
					String fechaReserva = buscaDatos(archivo, "fechaEvento");
					System.out.println("Fecha de la reserva: " + fechaReserva);
					System.out.print("\nInserte un número de los establecidos anteriormente: ");
					break;
				case "3":
					String tipoEvento = buscaDatos(archivo, "tipo");
					System.out.println("Tipo de evento: " + tipoEvento);
					System.out.print("\nInserte un número de los establecidos anteriormente: ");
					break;
				case "4":
					String asistentes = buscaDatos(archivo, "asistentes");
					System.out.println("Número de asistentes: " + asistentes);
					System.out.print("\nInserte un número de los establecidos anteriormente: ");
					break;
				case "5":
					String tipoCocina = buscaDatos(archivo, "tipoCocina");
					System.out.println("Tipo de cocina: " + tipoCocina);
					System.out.print("\nInserte un número de los establecidos anteriormente: ");
					break;
				case "6":
					String numeroJornadas = buscaDatos(archivo, "numeroJornadas");
					System.out.println("Número de jornadas: " + numeroJornadas);
					System.out.print("\nInserte un número de los establecidos anteriormente: ");
					break;
				case "7":
					String habitaciones = buscaDatos(archivo, "habitaciones");
					System.out.println("Número de habitaciones: " + habitaciones);
					System.out.print("\nInserte un número de los establecidos anteriormente: ");
					break;
				case "8":
					String tipoMesa = buscaDatos(archivo, "tipoMesa");
					System.out.println("Tipo de mesas: " + tipoMesa);
					System.out.print("\nInserte un número de los establecidos anteriormente: ");
					break;
				case "9":
					String comensalesMesa = buscaDatos(archivo, "comensalesMesa");
					System.out.println("Número de comensales por mesa: " + comensalesMesa);
					System.out.print("\nInserte un número de los establecidos anteriormente: ");
					break;
				default:
					System.out.println(
							"ERROR: Escoja un número entre el 0 y el 9 acorde a las opciones anteriormente listadas");
					break;
				}
			}
		}
	}

	public static File creaArchivo(Scanner sc, String extension) throws IOException {
		File archivo = null;
		String nombre;
		System.out.print("\nIntroduzca el nombre del archivo: ");
		nombre = sc.nextLine();
		if (nombre.endsWith(".txt")) {
			nombre = nombre.substring(0, nombre.length() - 4);
		}
		archivo = new File(nombre + extension);

		if (archivo.createNewFile()) {
			System.out.println("Se ha creado: " + archivo.getName());
		} else {
			System.out.println("Ya existe.");
		}

		return archivo;
	}

	public static File leeArchivoXML() throws IOException {
		File archivo = new File("xmlModel.txt");
		if (archivo.exists()) {
			return archivo;
		} else {
			System.err.println("El archivo xml no existe");
			return null;
		}
	}

	public static File leeArchivoJSON() throws IOException {
		File archivo = new File("jsonModel.txt");
		if (archivo.exists()) {
			return archivo;
		} else {
			System.err.println("El archivo json no existe");
			return null;
		}
	}

	public static void meteLineas(File archivo, Scanner sc) throws IOException {
		String contenido;
		boolean t = true;
		BufferedWriter bw = new BufferedWriter(new FileWriter(archivo.getName(), true));

		System.out.print("\nInserte el contenido a añadir en el texto (Doble 'Enter' para confirmar): ");
		while (t) {
			contenido = sc.nextLine();
			if (contenido.isEmpty()) {
				t = false;
			}
			bw.append(contenido);
			bw.newLine();
		}
		bw.close();
	}

	public static void muestraArchivo(File archivo, Scanner sc) throws IOException {
		String l;
		System.out.println("El contenido del archivo " + archivo.getName() + " es: ");
		System.out.println("----------------------------");
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			while ((l = br.readLine()) != null) {
				System.out.println(l);
			}
		}
	}

	public static String buscaDatos(File archivo, String campo) throws IOException {
		String inicioReserva = "<reserva>";
		String finReserva = "</reserva>";
		String inicio = "<" + campo + ">";
		String fin = "</" + campo + ">";
		String valorFinal = null;
		FileReader fR = new FileReader(archivo);
		BufferedReader lector = new BufferedReader(fR);
		String linea;
		StringBuilder contenido = new StringBuilder();
		boolean dentroDeReserva = false;

		while ((linea = lector.readLine()) != null) {
			contenido.append(linea + "\n");

			if (linea.contains(inicioReserva)) {
				dentroDeReserva = true;
			}

			if (dentroDeReserva && linea.contains(finReserva)) {
				dentroDeReserva = false;
			}

			if (dentroDeReserva && linea.contains(inicio)) {
				String pat = Pattern.quote(inicio) + "(.*?)" + Pattern.quote(fin);
				Pattern patron = Pattern.compile(pat);
				Matcher chiklin = patron.matcher(linea);

				if (chiklin.find()) {
					valorFinal = chiklin.group(1);
				}
			}
		}

		lector.close();

		return valorFinal;
	}

	public static void imprimeCoso(File archivo) throws IOException {
		String nombreReserva = buscaDatos(archivo, "nombre");
		System.out.println("Nombre: " + nombreReserva);
		String fechaReserva = buscaDatos(archivo, "fechaEvento");
		System.out.println("Fecha de la reserva: " + fechaReserva);
		String tipoEvento = buscaDatos(archivo, "tipo");
		System.out.println("Tipo de evento: " + tipoEvento);
		String asistentes = buscaDatos(archivo, "asistentes");
		System.out.println("Número de asistentes: " + asistentes);
		String tipoCocina = buscaDatos(archivo, "tipoCocina");
		System.out.println("Tipo de cocina: " + tipoCocina);
		String numeroJornadas = buscaDatos(archivo, "numeroJornadas");
		System.out.println("Número de jornadas: " + numeroJornadas);
		String habitaciones = buscaDatos(archivo, "habitaciones");
		System.out.println("Número de habitaciones: " + habitaciones);
		String tipoMesa = buscaDatos(archivo, "tipoMesa");
		System.out.println("Tipo de mesas: " + tipoMesa);
		String comensalesMesa = buscaDatos(archivo, "comensalesMesa");
		System.out.println("Número de comensales por mesa: " + comensalesMesa);
	}

	public static void deXMLaXML(File origen) throws IOException {
		Scanner sc = new Scanner(System.in);
		File destino = creaArchivo(sc, ".xml");
		FileReader fr = new FileReader(origen);
		BufferedReader lector = new BufferedReader(fr);
		BufferedWriter bw = new BufferedWriter(new FileWriter(destino));
		boolean dentroDeReserva = false;
		String linea;

		boolean eNombre = false;
		boolean eFechaEvento = false;
		boolean eTipoEvento = false;
		boolean eAsistentes = false;
		boolean eTipoCocina = false;
		boolean eNumeroJornadas = false;
		boolean eHabitaciones = false;
		boolean eTipoMesa = false;
		boolean eComensalesMesa = false;

		bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		bw.newLine();
		while ((linea = lector.readLine()) != null) {
			if (linea.contains("<reserva>")) {
				dentroDeReserva = true;
				bw.write("<reserva>");
				bw.newLine();
				eNombre = false;
				eFechaEvento = false;
				eTipoEvento = false;
				eAsistentes = false;
				eTipoCocina = false;
				eNumeroJornadas = false;
				eHabitaciones = false;
				eTipoMesa = false;
				eComensalesMesa = false;
			}

			if (dentroDeReserva) {
				eNombre = compruebaCampoXML(linea, "<nombre>", "</nombre>", eNombre, bw);
				eFechaEvento = compruebaCampoXML(linea, "<fechaEvento>", "</fechaEvento>", eFechaEvento, bw);
				eTipoEvento = compruebaCampoXML(linea, "<tipo>", "</tipo>", eTipoEvento, bw);
				eAsistentes = compruebaCampoXML(linea, "<asistentes>", "</asistentes>", eAsistentes, bw);
				eTipoCocina = compruebaCampoXML(linea, "<tipoCocina>", "</tipoCocina>", eTipoCocina, bw);
				eNumeroJornadas = compruebaCampoXML(linea, "<numeroJornadas>", "</numeroJornadas>", eNumeroJornadas,
						bw);
				eHabitaciones = compruebaCampoXML(linea, "<habitaciones>", "</habitaciones>", eHabitaciones, bw);
				eTipoMesa = compruebaCampoXML(linea, "<tipoMesa>", "</tipoMesa>", eTipoMesa, bw);
				eComensalesMesa = compruebaCampoXML(linea, "<comensalesMesa>", "</comensalesMesa>", eComensalesMesa,
						bw);
			}

			if (linea.contains("</reserva>")) {
				dentroDeReserva = false;
				bw.write("</reserva>");
				bw.newLine();
			}
		}

		lector.close();
		bw.close();
	}

	public static void deXMLaJSON(File origen) throws IOException {
		Scanner sc = new Scanner(System.in);
		File destino = creaArchivo(sc, ".json");
		FileReader fr = new FileReader(origen);
		BufferedReader lector = new BufferedReader(fr);
		BufferedWriter bw = new BufferedWriter(new FileWriter(destino));
		boolean dentroDeReserva = false;
		String linea;

		bw.write("{");
		bw.newLine();
		while ((linea = lector.readLine()) != null) {
			if (linea.contains("<reserva>")) {
				dentroDeReserva = true;
				bw.write("\"reserva\":{");
				bw.newLine();
			}

			if (dentroDeReserva) {
				compruebaCampodeXMLaJSON(linea, "nombre", "\"nombre\":", bw);
				compruebaCampodeXMLaJSON(linea, "fechaEvento", "\"fechaEvento\":", bw);
				compruebaCampodeXMLaJSON(linea, "tipo", "\"tipo\":", bw);
				compruebaCampodeXMLaJSON(linea, "asistentes", "\"asistentes\":", bw);
				compruebaCampodeXMLaJSON(linea, "tipoCocina", "\"tipoCocina\":", bw);
				compruebaCampodeXMLaJSON(linea, "numeroJornadas", "\"numeroJornadas\":", bw);
				compruebaCampodeXMLaJSON(linea, "habitaciones", "\"habitaciones\":", bw);
				compruebaCampodeXMLaJSON(linea, "tipoMesa", "\"tipoMesa\":", bw);
				compruebaCampodeXMLaJSON(linea, "comensalesMesa", "\"comensalesMesa\":", bw);
			}

			if (linea.contains("</reserva>")) {
				dentroDeReserva = false;
				bw.write("}");
				bw.newLine();
			}
		}
		bw.write("}");
		bw.newLine();
		lector.close();
		bw.close();
	}

	public static void deJSONaXML(File origen) throws IOException {
		Scanner sc = new Scanner(System.in);
		File destino = creaArchivo(sc, ".xml");
		FileReader fr = new FileReader(origen);
		BufferedReader lector = new BufferedReader(fr);
		BufferedWriter bw = new BufferedWriter(new FileWriter(destino));
		boolean dentroDeReserva = false;
		String linea;

		while ((linea = lector.readLine()) != null) {
			if (linea.contains("{")) {
				bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				bw.newLine();
			}
			if (linea.contains("\"reserva\":{")) {
				dentroDeReserva = true;
				bw.write("<reserva>");
				bw.newLine();
			}

			if (dentroDeReserva) {
				compruebaCampodeJSONaXML(linea, "nombre", bw);
				compruebaCampodeJSONaXML(linea, "fechaEvento", bw);
				compruebaCampodeJSONaXML(linea, "tipo", bw);
				compruebaCampodeJSONaXML(linea, "asistentes", bw);
				compruebaCampodeJSONaXML(linea, "tipoCocina", bw);
				compruebaCampodeJSONaXML(linea, "numeroJornadas", bw);
				compruebaCampodeJSONaXML(linea, "habitaciones", bw);
				compruebaCampodeJSONaXML(linea, "tipoMesa", bw);
				compruebaCampodeJSONaXML(linea, "comensalesMesa", bw);
			}

			if (linea.contains("}")) {
				dentroDeReserva = false;
				bw.write("</reserva>");
				bw.newLine();
			}
		}
		lector.close();
		bw.close();
	}

	public static void deJSONaJSON(File origen) throws IOException {
		Scanner sc = new Scanner(System.in);
		File destino = creaArchivo(sc, ".json");
		FileReader fr = new FileReader(origen);
		BufferedReader lector = new BufferedReader(fr);
		BufferedWriter bw = new BufferedWriter(new FileWriter(destino));
		boolean dentroDeReserva = false;
		String linea;

		bw.write("{");
		bw.newLine();
		while ((linea = lector.readLine()) != null) {
			if (linea.contains("\"reserva\":{")) {
				dentroDeReserva = true;
				bw.write("\"reserva\":{");
				bw.newLine();
			}

			if (dentroDeReserva) {
				compruebaCampoJSON(linea, "\"nombre\":", bw);
				compruebaCampoJSON(linea, "\"fechaEvento\":", bw);
				compruebaCampoJSON(linea, "\"tipo\":", bw);
				compruebaCampoJSON(linea, "\"asistentes\":", bw);
				compruebaCampoJSON(linea, "\"tipoCocina\":", bw);
				compruebaCampoJSON(linea, "\"numeroJornadas\":", bw);
				compruebaCampoJSON(linea, "\"habitaciones\":", bw);
				compruebaCampoJSON(linea, "\"tipoMesa\":", bw);
				compruebaCampoJSON(linea, "\"comensalesMesa\":", bw);
			}

			if (linea.contains("}")) {
				dentroDeReserva = false;
				bw.write("}");
				bw.newLine();
			}
		}
		lector.close();
		bw.close();
	}

	public static void compruebaCampodeXMLaJSON(String linea, String etiqueta, String sustituto, BufferedWriter bw)
			throws IOException {
		boolean condicion = linea.contains("<" + etiqueta + ">") && !linea.contains(sustituto)
				&& linea.endsWith("</" + etiqueta + ">");
		if (condicion) {
			linea = rodeaComillas(linea);
			linea = linea.replace("<" + etiqueta + ">", sustituto);
			linea = linea.replace("</" + etiqueta + ">", ",");
			bw.write(linea);
			bw.newLine();
		}
	}

	public static void compruebaCampodeJSONaXML(String linea, String etiqueta, BufferedWriter bw) throws IOException {
		boolean condicion = linea.contains("\"" + etiqueta + "\":");
		if (condicion) {
			String valor = quitaComillas(linea);
			linea = "<" + etiqueta + ">" + valor + "</" + etiqueta + ">";
			bw.write(linea);
			bw.newLine();
		}
	}

	public static String quitaComillas(String linea) {
		int inicio = linea.indexOf("\"") + 1;
		int fin = linea.lastIndexOf("\"");
		return linea.substring(inicio, fin);
	}

	public static String rodeaComillas(String linea) {
		int inicioValor = linea.indexOf(">");
		int finValor = linea.lastIndexOf("<");
		String valor = linea.substring(inicioValor + 1, finValor);
		linea = linea.replace(valor, "\"" + valor + "\"");
		return linea;
	}

	public static void compruebaCampoJSON(String linea, String campo, BufferedWriter bw) throws IOException {
		boolean condicion = linea.contains(campo);
		if (condicion) {
			bw.write(linea);
			bw.newLine();
		}
	}

	public static boolean compruebaCampoXML(String linea, String entrada, String salida, boolean check,
			BufferedWriter bw) throws IOException {
		boolean condicion = linea.contains(entrada) && !check && linea.endsWith(salida);
		if (condicion) {
			bw.write(linea);
			bw.newLine();
		}
		return check = true;
	}
}
