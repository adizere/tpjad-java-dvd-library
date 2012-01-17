package com.dvdworld.dbg;

import java.io.FileWriter;
import java.io.PrintWriter;

//
// Debug utility.
//
public class DWDbg {
	
	public static boolean useLog = true;
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
	
	public static void log(String debugString) {
		if (DWDbg.useLog == true)
			DWDbg.out(debugString);
	}
	
	public static void logStack(Exception e) {
		if (DWDbg.useLog == true) {
			StackTraceElement[] stackTrace = e.getStackTrace();
			for (int i = 0; i < stackTrace.length; i++) {
				DWDbg.log(stackTrace[i].toString());
			}
		}
	}
}