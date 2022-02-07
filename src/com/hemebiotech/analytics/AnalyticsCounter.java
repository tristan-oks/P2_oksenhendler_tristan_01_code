package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

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
	
	public static void main(String args[]) throws Exception {

			//String line = reader.readLine();

			//int i = 0;	// set i to 0 // inutile
			//int headCount = 0;	// counts headaches //devenu inutile
		symptomsList.sort(null); // tri alphabetique de la liste des symptomes. Va rendre le comptage beaucoup plus facile
		String oldSymptom = "";
		for (String line : symptomsList) {
				if (line.equals(oldSymptom)) {System.out.println("same symptom : " + line);}
				else {System.out.println("New symptom : " + line);
					oldSymptom = line;
					System.out.println("oldsymptom : " + oldSymptom);}
			}
		
		// next generate output
	try (// first get input
	BufferedReader reader = new BufferedReader (new FileReader("symptoms.txt"))) {
		FileWriter writer = new FileWriter ("result.out");
		writer.write("headache: " + headCount + "\n");
		writer.write("rash: " + rashCount + "\n");
		writer.write("pupils: " + pupilCount + "\n");
		writer.close();
	}
}}
