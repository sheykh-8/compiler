package com.twelve;

import java.io.*;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		//Give a file name as command-line argument to compiler to run the scanner.
		if (args.length > 0) {
			File f = new File(args[0]);
			try {
				InputStreamReader reader = new InputStreamReader(new FileInputStream(f));
				new Recognizer(reader);
				Parser p = new Parser();
				boolean res = p.parse();
				if (res) {
					System.out.println("Hooray!! this has the correct syntax!");
				} else {
					System.out.println("you fucked up somewhere");
				}
			} catch (Exception ex ) {
				System.out.println("this file doesn't exist.");
			}
		}
	}
}
