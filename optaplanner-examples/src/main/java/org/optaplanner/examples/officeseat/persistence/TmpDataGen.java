package org.optaplanner.examples.officeseat.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import org.optaplanner.examples.officeseat.domain.JobDomain;
import org.optaplanner.examples.officeseat.domain.JobRole;
import org.optaplanner.examples.officeseat.domain.SubjectMatter;

public class TmpDataGen {

	public static void main(String[] args) {
			
		for (int i = 1; i <= 40; i++) {
			StringBuilder sb = new StringBuilder();
			sb.append(i);
			sb.append(",Employee " + i);
			String jobDomain = getRandomStringFromEnum(JobDomain.class);
			sb.append("," + jobDomain);
			sb.append("," + getRandomStringFromEnum(JobRole.class));
			List<String> subjectMatterList = getRandomSubjectMatterStringList(JobDomain.valueOfCode(jobDomain), 3);
			sb.append("," + subjectMatterList.get(0));
			sb.append("," + subjectMatterList.get(1));
			sb.append("," + subjectMatterList.get(2));
			
			System.out.println(sb);
		}
	}

	private static String getRandomStringFromEnum(Class enumClass) {
		EnumSet set = EnumSet.allOf(enumClass);
		Object[] array = set.toArray();
		
		int rand = (int)(Math.random() * array.length);
		return array[rand].toString();
	}
	
	private static List<String> getRandomSubjectMatterStringList(JobDomain jobDomain, int num) {
		List<String> subjectMatterList = new ArrayList<String>();
		
		EnumSet set = EnumSet.allOf(SubjectMatter.class);
		Object[] array = set.toArray();
		
		Object[] range = null;
		if (jobDomain == JobDomain.PL) {
			range = Arrays.copyOfRange(array, 0, 18);
		} else if (jobDomain == JobDomain.MW) {
			range = Arrays.copyOfRange(array, 18, 33);
		} else if (jobDomain == JobDomain.ET) {
			range = Arrays.copyOfRange(array, 33, 39);
		}
		
		if (range.length < num) {
			throw new RuntimeException("range.length = " + range.length + ", num = " + num);
		}
		
		while (true) {
			int rand = (int)(Math.random() * range.length);
			String string = range[rand].toString();
			if (subjectMatterList.contains(string)) {
				continue;
			} else {
				subjectMatterList.add(string);
			}
			if (subjectMatterList.size() >= num) {
				break;
			}
		}
		
		return subjectMatterList;
	}
}
