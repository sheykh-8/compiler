package com.twelve;

import java.io.*;
import java.util.ArrayList;

public class Main {
	LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();

	public static void main(String[] args) {

		//Give a file name as command-line argument to compiler to run the scanner.
		if (args.length > 0) {
			File f = new File(args[0]);
			try {
				InputStreamReader reader = new InputStreamReader(new FileInputStream(f));
				new Recognizer(reader);
				Parser p = new Parser();
				p.parse();
			} catch (Exception ex ) {
				System.out.println("this file doesn't exist.");
			}
		}

		ArrayList<String> tokens = LexicalAnalyzer.scan("");

	}
}
