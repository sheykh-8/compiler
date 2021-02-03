package com.twelve;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {

    private static final String PATH = System.getProperty("java.io.tmpdir") + File.separator + "SinaSharp";

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
                    System.out.println("\nCompiled successfully");
                    new LanguageConverter(PATH); //Compile and Run converted code
                } else {
                    System.out.println("Error : Failed to compile");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Error : Source file is not available");
            }
        }
    }
}
