package com.hemebiotech.analytics;

import java.io.IOException;
import java.util.TreeMap;

/**
 * @author ------
 * 
 * Will read the TreeMap of symptoms and occurrences and write the file of the results
 *
 */
public interface IResultWriter {
	/**
	 * If no data is available, write an empty File
	 * 
	 * @return write a file containing a list of all different symptoms and the number of occurrences of each, sorted alphabetically
	 */
	
	void resultWriter(String fileName, TreeMap<String, Integer> map) throws IOException;
}