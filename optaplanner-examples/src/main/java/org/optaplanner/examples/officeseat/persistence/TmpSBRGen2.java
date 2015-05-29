package org.optaplanner.examples.officeseat.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class TmpSBRGen2 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(new File("/home/tkobayas/tmp-sbr.txt")));
		while(br.ready()) {
			String line = br.readLine();
			String string = line.replace(" ", "_");
			System.out.println(string + "(\"" + string + "\"),");
		}
	}

}
