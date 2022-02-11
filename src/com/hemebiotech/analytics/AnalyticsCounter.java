package com.hemebiotech.analytics;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

/**
 * 
 * @author ------
 *
 */

public class AnalyticsCounter {
		
	/**
	 * 
	 * lit un fichier de symptomes et crée un fichier des occurrences de chaque symptome trié alphanumériquement
	 * utilisera symptoms.txt en entrée et result.out en sortie par défaut,
	 * avec possibilité de changer cela dans la ligne de commande.
	 * @arg[0] fichier d'entrée
	 * @arg[1] fichier de sortie
	 *
	 */
	public static void main(String args[]) {
		String symptomsFile;
		String resultsFile;
		// List<String> symptomsList;
		
		if (args.length == 0) {symptomsFile = "symptoms.txt";resultsFile = "result.out";}
		else if (args.length == 1) {symptomsFile = args[0];resultsFile = "result.out";}
		else {symptomsFile = args[0];resultsFile = args[1];}
		//File f = new File(symptomsFile);
		//if(!f.isFile()) {System.out.println("Erreur : le fichier "+symptomsFile+" n'existe pas !");return;} 
		
		List<String> symptomsList = reading(symptomsFile);	// crée la liste de symptomes triés
		TreeMap<String, Integer> symptomsOccurence = count(symptomsList); //crée le dictionnaire symptomes,occurrences
		try {
			saving(resultsFile,symptomsOccurence) ; //écrit le fichier résultat
			} 
		catch (IOException e) {e.printStackTrace();}
		}
		
	/**
	 * 
	 * lit un fichier de symptomes et en retourne une liste triée alphanumériquement
	 * @param fileName un fichier de symptomes
	 * @return symptomsList une liste triée alphanumériquement
	 *
	 */
	static List<String> reading(String fileName)	{ 
		ISymptomReader symptomData = new ReadSymptomDataFromFile(fileName);
		List<String> symptomsList = symptomData.GetSymptoms();
		symptomsList.sort(null); 		// tri alphabetique de la liste des symptomes. Va rendre le comptage beaucoup plus facile
		return symptomsList;
	}
	
	/**
	 * 
	 * lit la liste des symptomes et en retourne un TreeMap comptant les occurences de chaque symptome
	 * @param list la liste des symptomes triée
	 * @return TreeMap un dictionnaire des symptomes et leurs occurrences
	 *
	 */
	static TreeMap<String,Integer> count(List<String> list) {
		TreeMap<String, Integer> symptomsOccurence = new TreeMap<String, Integer>();
		String oldSymptom = ""; // pour tester si le symptome change
		int occurence = 0;		// pour compter les occurrences des symptomes
		
		for (String line : list) {
				if (line.equals(oldSymptom)) {
					occurence += 1; 
					System.out.println("same symptom : " + line + ", occurence : " + occurence);
					symptomsOccurence.put(line,occurence);
					System.out.println("Map : " + line + " : " + symptomsOccurence.get(line)); // voir si la map se remplit bien
					}
				else {System.out.println("New symptom : " + line);
					occurence = 1;
					symptomsOccurence.put(line,occurence);
					System.out.println("Map : " + line + " : " + symptomsOccurence.get(line)); // voir si la map se remplit bien
					oldSymptom = line;
					}
			}
		return symptomsOccurence;
	}
	
	/**
	 * 
	 * lit le TreeMap des occurences et écrit le fichier des résultats	
	 * @param fileName le nom du fichier de sortie 
	 * @param map le dictionnaire des symptomes et de leurs occurrences
	 * 
	 */
	static void saving(String fileName, TreeMap<String,Integer> map) throws IOException {
		System.out.println("ecriture du fichier " + fileName);
		IResultWriter result = new WriteResultToFile(fileName, map);
		result.resultWriter(fileName, map);
		}
}
