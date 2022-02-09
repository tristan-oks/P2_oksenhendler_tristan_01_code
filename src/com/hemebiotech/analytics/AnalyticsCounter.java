package com.hemebiotech.analytics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @author ------
 *
 */
public class AnalyticsCounter {
		
	public static void main(String args[]) throws IOException {
		
		String symptomsFile;
		String resultsFile;
		if (args.length == 0) {symptomsFile = "symptoms.txt";resultsFile = "result.out";}
		else if (args.length == 1) {symptomsFile = args[0];resultsFile = "result.out";}
		else {symptomsFile = args[0];resultsFile = args[1];}
		File f = new File(symptomsFile);
		if(!f.isFile()) {System.out.println("Erreur : le fichier "+symptomsFile+" n'existe pas !");return;}
		List<String> symptomsList = readList(symptomsFile);
		TreeMap<String, Integer> symptomsOccurence = occurencesCount(symptomsList);
		fileWriter(resultsFile,symptomsOccurence);
	}
	
	static List<String> readList(String filename)	{  //lit le fichier des symptomes et en retourne une liste triée
		ISymptomReader symptomData = new ReadSymptomDataFromFile(filename);
		List<String> symptomsList = symptomData.GetSymptoms();
		symptomsList.sort(null); 		// tri alphabetique de la liste des symptomes. Va rendre le comptage beaucoup plus facile
		return symptomsList;
	}
	
	static TreeMap<String,Integer> occurencesCount(List<String> list) {// lit la liste des symptomes en retourne un TreeMap comptant les occurences
		TreeMap<String, Integer> symptomsOccurence = new TreeMap<String, Integer>();
		String oldSymptom = ""; // pour tester si le symptome change
		int occurence = 0;		// pour compter les occurences des symptomes
		
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
	
	static void fileWriter(String fileName, TreeMap<String,Integer> Map) throws IOException {// lit le TreeMap des occurences et écrit le fichier des résultats
		FileWriter fileWriter = new FileWriter(fileName, false);
		BufferedWriter writer = new BufferedWriter (fileWriter);
		for(Map.Entry<String,Integer> entry : Map.entrySet()) {
			writer.write(entry.getKey() + " : " + entry.getValue());
		    writer.newLine();			
		}
		writer.close();
	}
}