package com.hemebiotech.analytics;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * 
 * read a symptoms file and write a file containing the occurrences of each symptom sorted alphabetically.
 * use entry file symtoms.txt and create result.out by default, 
 * with option to change it by specifying other names in the command line
 * 
 * @author ------
 *
 */
// lit un fichier de symptomes et crée un fichier des occurrences de chaque symptome trié alphanumériquement
// utilisera symptoms.txt en entrée et result.out en sortie par défaut,
// avec possibilité de changer cela dans la ligne de commande.

public class AnalyticsCounter {
		
	/**
	 * 
	 * @param args[] entry file name + result file name
	 * args[0] fichier d'entrée
	 * args[1] fichier de sortie
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
		
		List<String> symptomsList = reading(symptomsFile);	// crée la liste de symptomes
		TreeMap<String, Integer> symptomsOccurence = count(symptomsList); //crée le dictionnaire symptomes,occurrences
		try {
			saving(resultsFile,symptomsOccurence) ; //écrit le fichier résultat
			} 
		catch (IOException e) {e.printStackTrace();}
		}
		
	/**
	 * 
	 * read the symptoms file and return a list.
	 //lit un fichier de symptomes et en retourne une liste
	 * 
	 * @param fileName symptoms file
	 * @return symptomsList alphabetically sorted list
	 *
	 */
	static List<String> reading(String fileName)	{ 
		ISymptomReader symptomData = new ReadSymptomDataFromFile(fileName);
		List<String> symptomsList = symptomData.GetSymptoms();
		return symptomsList;
	}
	
	/**
	 * 
	 * lit la liste des symptomes et en retourne un TreeMap comptant les occurrences de chaque symptome
	 * @param list la liste des symptomes triée
	 * @return TreeMap un dictionnaire des symptomes et leurs occurrences
	 *
	 */
	static TreeMap<String,Integer> count(List<String> list) {
		TreeMap<String, Integer> symptomsOccurence = new TreeMap<String, Integer>();
		
		for (String line : list) {
			System.out.println(line + ": " + Collections.frequency(list, line));
			symptomsOccurence.put(line,Collections.frequency(list, line));
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
		IResultWriter result = new WriteResultToFile();
		result.resultWriter(fileName, map);
		}
}
