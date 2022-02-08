package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author ------
 *
 */
public class AnalyticsCounter {
	private static int headCount = 0;	// initialize to 0 // nom corrigé
	private static int rashCount = 0;		// initialize to 0
	private static int pupilCount = 0;		// initialize to 0
	
	private static ReadSymptomDataFromFile symptomData = new ReadSymptomDataFromFile("symptoms.txt");
	private static List<String> symptomsList = symptomData.GetSymptoms();
	private static Map<String, Integer> symptomsOccurence = new HashMap<String, Integer>();
	
	public static void main(String args[]) throws Exception {

		symptomsList.sort(null); // tri alphabetique de la liste des symptomes. Va rendre le comptage beaucoup plus facile
		String oldSymptom = ""; // pour tester si le symptome change
		int occurence = 0;		// pour compter les occurences des symptomes
		
		for (String line : symptomsList) {
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
					
					//System.out.println("oldsymptom : " + oldSymptom);
					}
			}
		fileWriter("result.out");
	}
		
		// next generate output
	static void fileWriter(String fileName) throws IOException {
		FileWriter fileWriter = new FileWriter(fileName, false);
		BufferedWriter writer = new BufferedWriter (fileWriter);
		for(Map.Entry<String,Integer> entry : symptomsOccurence.entrySet()) {
			writer.write(entry.getKey() + " : " + entry.getValue());
		     // Retour à la ligne
			writer.newLine();
		}
		writer.close();
	}
}
		// result sort non trié, je capte pas