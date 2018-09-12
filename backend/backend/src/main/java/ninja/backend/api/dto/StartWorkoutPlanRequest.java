package ninja.backend.api.dto;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.validation.constraints.*;

import eu.execom.fabut.property.PropertyPath;


public class StartWorkoutPlanRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<BigDecimal> MAX_SQUAT = new PropertyPath<>("maxSquat");
    public static final PropertyPath<BigDecimal> MAX_BENCH = new PropertyPath<>("maxBench");
    public static final PropertyPath<BigDecimal> MAX_DEADLIFT = new PropertyPath<>("maxDeadlift");

    @NotNull
    private BigDecimal maxSquat;

    @NotNull
    private BigDecimal maxBench;

    @NotNull
    private BigDecimal maxDeadlift;

    private StartWorkoutPlanRequest() {
    }

    public StartWorkoutPlanRequest(BigDecimal maxSquat, BigDecimal maxBench, BigDecimal maxDeadlift) {
        this.maxSquat = maxSquat;
        this.maxBench = maxBench;
        this.maxDeadlift = maxDeadlift;
    }

    public BigDecimal getMaxSquat() {
        return maxSquat;
    }

    public BigDecimal getMaxBench() {
        return maxBench;
    }

    public BigDecimal getMaxDeadlift() {
        return maxDeadlift;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final StartWorkoutPlanRequest other = (StartWorkoutPlanRequest) obj;
        if (this.maxSquat != null && other.maxSquat != null && !this.maxSquat.equals(other.maxSquat))
            return false;
        if (this.maxBench != null && other.maxBench != null && !this.maxBench.equals(other.maxBench))
            return false;
        if (this.maxDeadlift != null && other.maxDeadlift != null && !this.maxDeadlift.equals(other.maxDeadlift))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.maxSquat == null) ? 0 : this.maxSquat.hashCode());
        result = prime * result + ((this.maxBench == null) ? 0 : this.maxBench.hashCode());
        result = prime * result + ((this.maxDeadlift == null) ? 0 : this.maxDeadlift.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "StartWorkoutPlanRequest[" + "this.maxSquat=" + this.maxSquat + ", this.maxBench=" + this.maxBench + ", this.maxDeadlift=" + this.maxDeadlift + "]";
    }

}
