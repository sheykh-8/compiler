package com.twelve;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();

	public static void main(String[] args) {
		if (args.length > 0) {
			File f = new File(args[0]);
			try {
				InputStreamReader reader = new InputStreamReader(new FileInputStream(f));
				System.out.println(reader.read());
				new Recognizer(reader);
			} catch (Exception ex ) {
				System.out.println("this file doesn't exist.");
			}
		}

		ArrayList<String> tokens = LexicalAnalyzer.scan("");

	}
}
