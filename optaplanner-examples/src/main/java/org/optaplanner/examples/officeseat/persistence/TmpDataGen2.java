package org.optaplanner.examples.officeseat.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.optaplanner.examples.officeseat.domain.JobDomain;
import org.optaplanner.examples.officeseat.domain.JobRole;
import org.optaplanner.examples.officeseat.domain.SubjectMatter;

public class TmpDataGen2 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader(new File(
				"/home/tkobayas/mywork/sbr05.txt")));

		int i = 1;
		while (br.ready()) {
			String name = br.readLine();

			StringBuilder sb = new StringBuilder();

			sb.append(i++);
			sb.append("," + name);
			sb.append(",PL");
			sb.append(",TSE");

			String sbr = br.readLine();

			sb.append("," + sbr);
			
			System.out.println(sb.toString());
		}
	}
}