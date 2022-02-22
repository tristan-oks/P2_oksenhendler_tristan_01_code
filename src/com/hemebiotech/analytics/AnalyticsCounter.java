package com.hemebiotech.analytics;

import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * read a symptoms file and write a file containing the occurrences of each symptom sorted alphabetically.
 * use entry file symtoms.txt and create result.out by default,
 * with option to change it by specifying other names in the command line
 *
 * @author oksenhendler tristan
 *
 */
public class AnalyticsCounter {

	/**
	 *
	 * @param args[] entry file name + result file name
	 * 		args[0] input file name
	 * 		args[1] output file name
	 *
	 */
	public static void main(String args[]){
		String[] fileNames = getFilesNames(args);
		List<String> symptomsList = reading(fileNames[0]);
		TreeMap<String, Integer> symptomsOccurence = count(symptomsList);
		saving(fileNames[1], symptomsOccurence);
	}

	/**
	 * read the command line arguments if exist and return input and output file names
	 * 
	 * @param args command line arguments
	 * @return filenames input and output files names
	 * 
	 */
	static String[] getFilesNames(String args[]) {
		String[] fileNames = {"symptoms.txt","results.out"};
		if (args.length == 1) {
			fileNames[0] = args[0];
		} else if (args.length == 2){
			fileNames[0] = args[0];
			fileNames[1] = args[1];
		}
		return fileNames;
	}

	/**
	 *
	 * read the symptoms file and return a list.
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
	 *	read the symptoms list and return a Treemap counting each symptom occurrences
	 *
	 * 	@param 	list symptoms list
	 * 	@return symptomsOccurence Treemap of symptoms and occurrences
	 *
	 */
	static TreeMap<String,Integer> count(List<String> list) {
		TreeMap<String, Integer> symptomsOccurence = new TreeMap<>();

		for (String line : list) {
			symptomsOccurence.put(line,Collections.frequency(list, line));
		}
		return symptomsOccurence;
	}

	/**
	 *
	 * read the Treemap counting each symptom occurrences and write the output file
	 * 
	 * @param fileName output file name
	 * @param map Treemap of symptoms and occurrences
	 *
	 */
	static void saving(String fileName, TreeMap<String,Integer> map) {
		IResultWriter result = new WriteResultToFile();
		result.resultWriter(fileName, map);
	}
}
