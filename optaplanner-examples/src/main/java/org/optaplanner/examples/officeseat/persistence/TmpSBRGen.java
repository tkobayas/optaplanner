package org.optaplanner.examples.officeseat.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class TmpSBRGen {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(new File("/home/tkobayas/mywork/sbr04.txt")));
		boolean newName = false;
		StringBuilder sb = new StringBuilder();
		while(br.ready()) {
			String line = br.readLine();
			if (line.indexOf(", ") != -1) {
				String[] split = line.split(", ");
				String name = split[1] + " " + split[0];
				System.out.println(sb.toString());
				System.out.println(name);
				newName = true;
				continue;
			}
			if (!newName) {
				sb.append(",");
				sb.append(line);
			} else {
				sb = new StringBuilder();
				sb.append(line);
				newName = false;
			}
		}
		System.out.println(sb.toString());
	}

}
