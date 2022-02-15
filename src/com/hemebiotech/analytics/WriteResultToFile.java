package com.hemebiotech.analytics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * Will read the TreeMap of symptoms and occurrences and write the file of the results
 * 
 * @author oksenhendler tristan
 *
 */
public class WriteResultToFile implements IResultWriter {
	
	public WriteResultToFile() {
		super();
	}

	/**
	 * 
	 * Will read the TreeMap of symptoms and occurrences and write the file of the results
	 * 
	 * @param fileName the name of the results file; 
	 * @param map the Treemap of the symptoms and occurrences
	 */
	@Override
	public void resultWriter(String fileName, TreeMap<String,Integer> map) {
		
		try {
			FileWriter fileWriter = new FileWriter(fileName, false);	
			BufferedWriter writer = new BufferedWriter (fileWriter);
			for(Map.Entry<String,Integer> entry : map.entrySet()) {
				writer.write(entry.getKey() + " : " + entry.getValue());
			    writer.newLine();			
			}
			writer.close();
			} catch (IOException e) {
			System.out.println("Error!");
		}
	}
}
