package org.optaplanner.examples.cheaptime.solver.drools;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Period {

    private int period;

    public Period(int period) {
        this.period = period;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Period) {
            Period other = (Period) o;
            return new EqualsBuilder()
                    .append(period, other.period)
                    .isEquals();
        } else {
            return false;
        }
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(period)
                .toHashCode();
    }

    public int compareTo(Period other) {
        return new CompareToBuilder()
                .append(period, other.period)
                .toComparison();
    }

    @Override
    public String toString() {
        return String.valueOf(period);
    }
}
