package com.hemebiotech.analytics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author ------
 * 
 * Will read the TreeMap of symptoms and occurrences and write the file of the results
 *
 */

public class WriteResultToFile implements IResultWriter {
	
	public WriteResultToFile(String fileName, TreeMap<String,Integer> map) {
	}

	@Override
	public void resultWriter(String fileName, TreeMap<String,Integer> map) throws IOException {
		/**
		 * 
		 * @param fileName the name of the results file; 
		 * @param map the Treemap of the symptoms and occurrences
		 */
		System.out.println("resultWriter " + fileName);
		FileWriter fileWriter = new FileWriter(fileName, false);
		BufferedWriter writer = new BufferedWriter (fileWriter);
		try {
			for(Map.Entry<String,Integer> entry : map.entrySet()) {
				writer.write(entry.getKey() + " : " + entry.getValue());
			    writer.newLine();			
			}
		} catch (IOException e) {e.printStackTrace();}
		writer.close();
	}
}
