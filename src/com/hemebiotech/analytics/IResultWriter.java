package com.hemebiotech.analytics;

import java.io.IOException;
import java.util.TreeMap;

/**
 * 
 * Will read the TreeMap of symptoms and occurrences and write the file of the results
 * 
 * @author ------
 *
 */
public interface IResultWriter {
	/**
	 * write a file containing a list of all different symptoms and the number of occurrences of each, sorted alphabetically. 
	 * If no data is available, write an empty File	 
	 * 
	 * 
	 * @param fileName the name of the results file; 
	 * @param map the Treemap of the symptoms and occurrences
	 */
	
	void resultWriter(String fileName, TreeMap<String, Integer> map) throws IOException;
}