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
    private final List<TaskAssignment> taskAssignmentList;

    private MachinePeriodStatus status;
    private List<Integer> resourceAvailableList;

    public MachinePeriodPart(Machine machine, int period, int resourceListSize, List<TaskAssignment> taskAssignmentList) {
        this.machine = machine;
        this.period = period;
        this.taskAssignmentList = taskAssignmentList;
        
        status = MachinePeriodStatus.OFF;
        resourceAvailableList = new ArrayList<Integer>(resourceListSize);
        for (int i = 0; i < resourceListSize; i++) {
            resourceAvailableList.add(machine.getMachineCapacityList().get(i).getCapacity());
        }

        for (TaskAssignment taskAssignment : taskAssignmentList) {
            addTaskAssignment(taskAssignment);
        }
    }

    private void addTaskAssignment(TaskAssignment taskAssignment) {
        status = MachinePeriodStatus.ACTIVE;
        Task task = taskAssignment.getTask();
        for (int i = 0; i < resourceAvailableList.size(); i++) {
            int resourceAvailable = resourceAvailableList.get(i);
            TaskRequirement taskRequirement = task.getTaskRequirementList().get(i);
            resourceAvailableList.set(i, resourceAvailable - taskRequirement.getResourceUsage());
        }
    }

    public MachinePeriodStatus getStatus() {
        return status;
    }

    public void setStatus(MachinePeriodStatus status) {
        this.status = status;
    }

    public List<Integer> getResourceAvailableList() {
        return resourceAvailableList;
    }

    public void setResourceAvailableList(List<Integer> resourceAvailableList) {
        this.resourceAvailableList = resourceAvailableList;
    }

    public Machine getMachine() {
        return machine;
    }

    public int getPeriod() {
        return period;
    }
    
    public List<TaskAssignment> getTaskAssignmentList() {
        return taskAssignmentList;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof MachinePeriodPart) {
            MachinePeriodPart other = (MachinePeriodPart) o;
            return new EqualsBuilder()
                    .append(machine, other.machine)
                    .append(period, other.period)
                    .append(status, other.status)
                    .append(resourceAvailableList, other.resourceAvailableList)
                    .append(taskAssignmentList, other.taskAssignmentList)
                    .isEquals();
        } else {
            return false;
        }
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(machine)
                .append(period)
                .append(status)
                .append(resourceAvailableList)
                .append(taskAssignmentList)
                .toHashCode();
    }

    public int compareTo(MachinePeriodPart other) {
        return new CompareToBuilder()
                .append(machine, other.machine)
                .append(period, other.period)
                .append(status, other.status)
                .append(resourceAvailableList, other.resourceAvailableList)
                .append(taskAssignmentList, other.taskAssignmentList)
                .toComparison();
    }

    @Override
    public String toString() {
        return machine + ", period = " + period + ", status = " + status + ", resourceAvailableList = " + resourceAvailableList + ", taskAssignmentList = " + taskAssignmentList;
    }
}
