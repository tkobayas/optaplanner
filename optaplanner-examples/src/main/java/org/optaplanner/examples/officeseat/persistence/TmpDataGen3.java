package org.optaplanner.examples.officeseat.persistence;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.optaplanner.examples.officeseat.domain.JobDomain;
import org.optaplanner.examples.officeseat.domain.JobRole;
import org.optaplanner.examples.officeseat.domain.SubjectMatter;

public class TmpDataGen3 {

	public static void main(String[] args) {
			
		for (int i = 1; i <= 40; i++) {
			StringBuilder sb = new StringBuilder();
			sb.append(i);
			sb.append(",Employee " + i);
			sb.append("," + getRandomStringFromEnum(JobDomain.class));
			sb.append("," + getRandomStringFromEnum(JobRole.class));
			List<String> subjectMatterList = getRandomStringListFromEnum(SubjectMatter.class, 3);
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
	
	private static List<String> getRandomStringListFromEnum(Class enumClass, int num) {
		List<String> subjectMatterList = new ArrayList<String>();
		
		EnumSet set = EnumSet.allOf(enumClass);
		Object[] array = set.toArray();
		
		if (array.length < num) {
			throw new RuntimeException("array.length = " + array.length + ", num = " + num);
		}
		
		while (true) {
			int rand = (int)(Math.random() * array.length);
			String string = array[rand].toString();
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
