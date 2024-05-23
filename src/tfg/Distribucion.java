package tfg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Distribucion {

	public static DisposicionGrupal distribuir(List<Persona> numbers, int numGroups, int limitPerGroup) {

		List<DisposicionGrupal> result = new ArrayList<>();
		// List<List<List<Persona>>> result = new ArrayList<>();
		DisposicionGrupal currentCombination = new DisposicionGrupal();
		// List<List<Persona>> currentCombination = new ArrayList<>();
		for (int i = 0; i < numGroups; i++) {
			currentCombination.addGrupo(new Grupo());
		}
		generateCombinations(numbers, 0, currentCombination, result, limitPerGroup);
		System.out.println("Todas las combinaciones posibles de " + numGroups + " subgrupos con un máximo de "
				+ limitPerGroup + " números por subgrupo:");

		List<DisposicionGrupal> filteredCombinations = filterCombinations(result, 0, 1);
		if (filteredCombinations.isEmpty()) {
			filteredCombinations = filterCombinations(result, 1, 2);
		}
		if (filteredCombinations.isEmpty()) {
			filteredCombinations = filterCombinations(result, 2, 3);
		}
		if (filteredCombinations.isEmpty()) {
			filteredCombinations = filterCombinations(result, 3, 4);
		}

		for (DisposicionGrupal combination : result) {
			System.out.print("[");
			for (Grupo group : combination.getListaGrupos()) {
				System.out.print("[");
				for (Persona p : group.getListaPersonas()) {
					System.out.print(p + "-" + p.calcularTension(group.getListaPersonas()) + "|");
				}
				System.out.print("]");
			}
			System.out.print("]\n");

			System.out.println("Medias de los subgrupos: " + combination.getMedias());
			System.out.println("Media de las medias: " + combination.calculateOverallMean());
			System.out.println("Rango de las medias: " + combination.calculateRange());
		}
		if (!filteredCombinations.isEmpty()) {
			DisposicionGrupal combinationWithMinRange = getCombinationWithMinRange(filteredCombinations);
			System.out.println("Combinación con el menor rango de medias: " + combinationWithMinRange);

		} else {
			System.out.println("No se encontraron combinaciones filtradas.");
		}

		return getCombinationWithMinRange(filteredCombinations);

	}

	private static void generateCombinations(List<Persona> numbers, int index, DisposicionGrupal currentCombination,
			List<DisposicionGrupal> result, int limitPerGroup) {
		if (index == numbers.size()) {
			DisposicionGrupal combination = new DisposicionGrupal();
			for (Grupo group : currentCombination.getListaGrupos()) {
				combination.addGrupo(group);
			}
			result.add(combination);
			return;
		}

		Persona currentNumber = numbers.get(index);
		for (int i = 0; i < currentCombination.getListaGrupos().size(); i++) {
			if (currentCombination.getGrupo(i).getListaPersonas().size() < limitPerGroup) { // Limitar a 'limitPerGroup'
																							// nÃºmeros por subgrupo
				currentCombination.getGrupo(i).addPersona(currentNumber);
				generateCombinations(numbers, index + 1, currentCombination, result, limitPerGroup);
				currentCombination.getGrupo(i).getListaPersonas()
						.remove(currentCombination.getGrupo(i).getListaPersonas().size() - 1);
			}
		}
	}

	private static List<DisposicionGrupal> filterCombinations(List<DisposicionGrupal> combinations, double minRange,
			double maxRange) {
		List<DisposicionGrupal> filteredCombinations = new ArrayList<>();
		for (DisposicionGrupal combination : combinations) {
			double overallMean = combination.calculateOverallMean();
			if (overallMean >= minRange && overallMean < maxRange) {
				filteredCombinations.add(combination);
			}
		}
		return filteredCombinations;
	}

	private static DisposicionGrupal getCombinationWithMinRange(List<DisposicionGrupal> combinations) {
		DisposicionGrupal minRangeCombination = null;
		double minRange = Double.MAX_VALUE;
		for (DisposicionGrupal combination : combinations) {
			double range = combination.calculateRange();
			if (range < minRange) {
				minRange = range;
				minRangeCombination = combination;
			}
		}
		return minRangeCombination;
	}

	public static void crearArchivoXML(List<Grupo> grupos, Informe informe) {
        try {
            File file = new File("informe.xml");
            FileWriter writer = new FileWriter(file);

            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<informe>\n");
            writer.write("  <autor>" + informe.getNombre() + "</autor>\n");
            writer.write("  <participantes>" + informe.getValor() + "</participantes>\n");
            writer.write("  <grupos>\n");

            for (Grupo grupo : grupos) {
                writer.write("    <grupo>\n");
                writer.write("      <participante>\n");
                for (Persona persona : grupo.getListaPersonas()) {
                    writer.write("        <nombre>" + persona.getNombre() + "</nombre>\n");
                }
                writer.write("      </participante>\n");
                writer.write("    </grupo>\n");
            }

            writer.write("  </grupos>\n");
            writer.write("</informe>");

            writer.close();
            System.out.println("El archivo XML se ha creado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo XML: " + e.getMessage());
        }
    }
}