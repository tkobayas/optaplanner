package org.optaplanner.examples.officeseat.domain;

import java.util.List;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.examples.common.domain.AbstractPersistable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@PlanningEntity
@XStreamAlias("Employee")
public class Employee extends AbstractPersistable {

	private long code;
	private String name;
	
	private JobDomain jobDomain;
	private JobRole jobRole;
	private List<SubjectMatter> subjectMatterList;
	
	private Seat seat;
	
	@PlanningVariable(valueRangeProviderRefs = {"seatList"})
	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	
	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JobDomain getJobDomain() {
		return jobDomain;
	}

	public void setJobDomain(JobDomain jobDomain) {
		this.jobDomain = jobDomain;
	}

	public JobRole getJobRole() {
		return jobRole;
	}

	public void setJobRole(JobRole jobRole) {
		this.jobRole = jobRole;
	}

	public List<SubjectMatter> getSubjectMatterList() {
		return subjectMatterList;
	}

	public void setSubjectMatterList(List<SubjectMatter> subjectMatterList) {
		this.subjectMatterList = subjectMatterList;
	}
	
	public String toString() {
		return "[Employee] id = " + id + ", code = " + code + ", name = " + name;
	}
	
	// -----------------------
	// Complex methods
	// -----------------------
	
	public boolean isSubjectMatterOverlapped(Employee other) {
		List<SubjectMatter> othersList = other.getSubjectMatterList();
		for (SubjectMatter subjectMatter : subjectMatterList) {
			if (othersList.contains(subjectMatter)) {
				return true;
			}
		}
		return false;
	}
	


}
