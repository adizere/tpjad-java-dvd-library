package com.dvdworld.dbg;

import java.io.FileWriter;
import java.io.PrintWriter;

//
// Debug utility.
//
public class DWDbg {
	
	public static String debugFilePath = "c:\\a.txt";
	
	public DWDbg() {
	}
	
	public static void out(String debugString) {
		PrintWriter pw = null;
		try{
			// Create file if this doesn't exist yet and append text to the file.
			FileWriter fstream = new FileWriter(debugFilePath, true);
			pw = new PrintWriter(fstream);
			pw.println(debugString);
			pw.flush();
		} catch (Exception e) {
			System.err.println("Debug handling exception: " + e.getMessage());
		} finally {
			pw.close();
		}
	}
}