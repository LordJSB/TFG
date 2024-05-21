package tfg;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Distribucion {

	 public static  List<List<Persona>> distribuir(List<Persona> numbers,int numGroups,int limitPerGroup) {
	      

	        List<List<List<Persona>>> result = new ArrayList<>();
	        List<List<Persona>> currentCombination = new ArrayList<>();
	        for (int i = 0; i < numGroups; i++) {
	            currentCombination.add(new ArrayList<>());
	        }

	        generateCombinations(numbers, 0, currentCombination, result, limitPerGroup);

	        System.out.println("Todas las combinaciones posibles de " + numGroups + " subgrupos con un máximo de " + limitPerGroup + " números por subgrupo:");
	        
	        List<List<List<Persona>>> filteredCombinations = filterCombinations(result, 0, 1);
	        if (filteredCombinations.isEmpty()) {
	            filteredCombinations = filterCombinations(result, 1, 2);
	        }
	        if (filteredCombinations.isEmpty()) {
	            filteredCombinations = filterCombinations(result, 2, 3);
	        }
	        if (filteredCombinations.isEmpty()) {
	            filteredCombinations = filterCombinations(result, 3, 4);
	        }

	        for (List<List<Persona>> combination : result) {
	        	System.out.print("[");
	            for (List<Persona> group : combination) {
	            	System.out.print("[");
	            	 for (Persona p : group) {
	 	            	System.out.print(p+"-"+p.calcularTension(group)+"|");
	 	            }
	            	 System.out.print("]");
	            }
	            System.out.print("]\n");
	            List<Double> means = calculateMeans(combination);
	            System.out.println("Medias de los subgrupos: " + means);
	            System.out.println("Media de las medias: " + calculateOverallMean(means));
	            System.out.println("Rango de las medias: " + calculateRange(means));
	        }
	        if (!filteredCombinations.isEmpty()) {
	            List<List<Persona>> combinationWithMinRange = getCombinationWithMinRange(filteredCombinations);
	            System.out.println("Combinación con el menor rango de medias: " + combinationWithMinRange);
	           
	        } else {
	            System.out.println("No se encontraron combinaciones filtradas.");
	        }
	        
	        return getCombinationWithMinRange(filteredCombinations);
	        
	    }

	    private static void generateCombinations(List<Persona> numbers, int index, List<List<Persona>> currentCombination, List<List<List<Persona>>> result, int limitPerGroup) {
	        if (index == numbers.size()) {
	            List<List<Persona>> combination = new ArrayList<>();
	            for (List<Persona> group : currentCombination) {
	                combination.add(new ArrayList<>(group));
	            }
	            result.add(combination);
	            return;
	        }

	        Persona currentNumber = numbers.get(index);
	        for (int i = 0; i < currentCombination.size(); i++) {
	            if (currentCombination.get(i).size() < limitPerGroup) { // Limitar a 'limitPerGroup' números por subgrupo
	                currentCombination.get(i).add(currentNumber);
	                generateCombinations(numbers, index + 1, currentCombination, result, limitPerGroup);
	                currentCombination.get(i).remove(currentCombination.get(i).size() - 1);
	            }
	        }
	    }

	    private static List<Double> calculateMeans(List<List<Persona>> combination) {
	        List<Double> means = new ArrayList<>();
	        for (List<Persona> subgroup : combination) {
	            if (subgroup.isEmpty()) {
	                means.add(0.0);
	            } else {
	                double sum = 0;
	                for (Persona num : subgroup) {
	                	if(num!=null) {
	                    sum += num.calcularTension(subgroup);
	                	}
	                	else {
	                		sum += 0;
	                	}
	                }
	                means.add(sum / subgroup.size());
	            }
	        }
	        return means;
	    }

	    private static double calculateOverallMean(List<Double> means) {
	        if (means.isEmpty()) {
	            return 0.0;
	        }
	        double sum = 0;
	        for (double mean : means) {
	            sum += mean;
	        }
	        return sum / means.size();
	    }

	    private static double calculateRange(List<Double> means) {
	        if (means.isEmpty()) {
	            return 0.0;
	        }
	        double min = Collections.min(means);
	        double max = Collections.max(means);
	        return max - min;
	    }

	    private static List<List<List<Persona>>> filterCombinations(List<List<List<Persona>>> combinations, double minRange, double maxRange) {
	        List<List<List<Persona>>> filteredCombinations = new ArrayList<>();
	        for (List<List<Persona>> combination : combinations) {
	            List<Double> means = calculateMeans(combination);
	            double overallMean = calculateOverallMean(means);
	            if (overallMean >= minRange && overallMean < maxRange) {
	                filteredCombinations.add(combination);
	            }
	        }
	        return filteredCombinations;
	    }

	    private static List<List<Persona>> getCombinationWithMinRange(List<List<List<Persona>>> combinations) {
	        List<List<Persona>> minRangeCombination = null;
	        double minRange = Double.MAX_VALUE;
	        for (List<List<Persona>> combination : combinations) {
	            List<Double> means = calculateMeans(combination);
	            double range = calculateRange(means);
	            if (range < minRange) {
	                minRange = range;
	                minRangeCombination = combination;
	            }
	        }
	        return minRangeCombination;
	    }
	}
