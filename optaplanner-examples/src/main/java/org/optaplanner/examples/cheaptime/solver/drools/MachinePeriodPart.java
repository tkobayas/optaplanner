package org.optaplanner.examples.cheaptime.solver.drools;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.optaplanner.examples.cheaptime.domain.Machine;
import org.optaplanner.examples.cheaptime.domain.Task;
import org.optaplanner.examples.cheaptime.domain.TaskAssignment;
import org.optaplanner.examples.cheaptime.domain.TaskRequirement;

public class MachinePeriodPart {
    private final Machine machine;
    private final int period;

    private boolean active;
    private int[] resourceAvailableList;
    private int resourceInShortTotal;

    public MachinePeriodPart(Machine machine, int period, int resourceListSize, List<TaskAssignment> taskAssignmentList) {
        this.machine = machine;
        this.period = period;

        active = false;

        resourceAvailableList = new int[resourceListSize];
        for (int i = 0; i < resourceListSize; i++) {
            resourceAvailableList[i] = machine.getMachineCapacityList().get(i).getCapacity();
        }

        for (TaskAssignment taskAssignment : taskAssignmentList) {
            addTaskAssignment(taskAssignment);
        }

	    resourceInShortTotal = 0;
	    for (int resourceAvailable : resourceAvailableList) {
	        if (resourceAvailable < 0) {
	            resourceInShortTotal += resourceAvailable;
	        }
	    }
    }

    private void addTaskAssignment(TaskAssignment taskAssignment) {
        active = true;
        Task task = taskAssignment.getTask();
        for (int i = 0; i < resourceAvailableList.length; i++) {
            TaskRequirement taskRequirement = task.getTaskRequirementList().get(i);
            resourceAvailableList[i] -=  taskRequirement.getResourceUsage();
        }
    }

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int[] getResourceAvailableList() {
        return resourceAvailableList;
    }

    public void setResourceAvailableList(int[] resourceAvailableList) {
        this.resourceAvailableList = resourceAvailableList;
    }

    public int getResourceInShortTotal() {
		return resourceInShortTotal;
	}

    public Machine getMachine() {
        return machine;
    }

    public int getPeriod() {
        return period;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof MachinePeriodPart) {
            MachinePeriodPart other = (MachinePeriodPart) o;
            return new EqualsBuilder()
                    .append(machine, other.machine)
                    .append(period, other.period)
                    .append(active, other.active)
                    .append(resourceAvailableList, other.resourceAvailableList)
                    .isEquals();
        } else {
            return false;
        }
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(machine)
                .append(period)
                .append(active)
                .append(resourceAvailableList)
                .toHashCode();
    }

    public int compareTo(MachinePeriodPart other) {
        return new CompareToBuilder()
                .append(machine, other.machine)
                .append(period, other.period)
                .append(active, other.active)
                .append(resourceAvailableList, other.resourceAvailableList)
                .toComparison();
    }

    @Override
    public String toString() {
        return machine + ", period = " + period + ", active = " + active + ", resourceAvailableList = " + resourceAvailableList;
    }
}
