package org.optaplanner.examples.officeseat.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;
import org.optaplanner.core.impl.score.buildin.hardsoftlong.HardSoftLongScoreDefinition;
import org.optaplanner.examples.common.domain.AbstractPersistable;
import org.optaplanner.persistence.xstream.impl.score.XStreamScoreConverter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@PlanningSolution
@XStreamAlias("OfficeSeatArrangement")
public class OfficeSeat extends AbstractPersistable implements Solution<HardSoftLongScore> {

	private List<Employee> employeeList;
	private List<Seat> seatList;
	
	private int seatCol;
	private int seatRow;

    @XStreamConverter(value = XStreamScoreConverter.class, types = {HardSoftLongScoreDefinition.class})
    private HardSoftLongScore score;
	
    public HardSoftLongScore getScore() {
        return score;
    }

    public void setScore(HardSoftLongScore score) {
        this.score = score;
    }

	@PlanningEntityCollectionProperty
	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	@ValueRangeProvider(id = "seatList")
	public List<Seat> getSeatList() {
		return seatList;
	}

	public void setSeatList(List<Seat> seatList) {
		this.seatList = seatList;
	}
    
	public int getSeatCol() {
		return seatCol;
	}

	public void setSeatCol(int seatCol) {
		this.seatCol = seatCol;
	}

	public int getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}
	
    // ************************************************************************
    // Complex methods
    // ************************************************************************

    public Collection<? extends Object> getProblemFacts() {
        List<Object> facts = new ArrayList<Object>();
        facts.addAll(seatList);
        return facts;
    }

}
