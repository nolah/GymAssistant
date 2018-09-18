package org.ninja.gymassistant.dto;


import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

import java.math.BigDecimal;



public class UpdateWorkoutRequestExercises implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id;

    @NotNull
    private Integer set1Reps;

    @NotNull
    private BigDecimal set1Weight;

    @NotNull
    private Integer set2Reps;

    @NotNull
    private BigDecimal set2Weight;

    @NotNull
    private Integer set3Reps;

    @NotNull
    private BigDecimal set3Weight;

    private UpdateWorkoutRequestExercises() {
    }

    public UpdateWorkoutRequestExercises(Long id, Integer set1Reps, BigDecimal set1Weight, Integer set2Reps, BigDecimal set2Weight, Integer set3Reps, BigDecimal set3Weight) {
        this.id = id;
        this.set1Reps = set1Reps;
        this.set1Weight = set1Weight;
        this.set2Reps = set2Reps;
        this.set2Weight = set2Weight;
        this.set3Reps = set3Reps;
        this.set3Weight = set3Weight;
    }

    public Long getId() {
        return id;
    }

    public Integer getSet1Reps() {
        return set1Reps;
    }

    public BigDecimal getSet1Weight() {
        return set1Weight;
    }

    public Integer getSet2Reps() {
        return set2Reps;
    }

    public BigDecimal getSet2Weight() {
        return set2Weight;
    }

    public Integer getSet3Reps() {
        return set3Reps;
    }

    public BigDecimal getSet3Weight() {
        return set3Weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final UpdateWorkoutRequestExercises other = (UpdateWorkoutRequestExercises) obj;
        if (this.id != null && other.id != null && !this.id.equals(other.id))
            return false;
        if (this.set1Reps != null && other.set1Reps != null && !this.set1Reps.equals(other.set1Reps))
            return false;
        if (this.set1Weight != null && other.set1Weight != null && !this.set1Weight.equals(other.set1Weight))
            return false;
        if (this.set2Reps != null && other.set2Reps != null && !this.set2Reps.equals(other.set2Reps))
            return false;
        if (this.set2Weight != null && other.set2Weight != null && !this.set2Weight.equals(other.set2Weight))
            return false;
        if (this.set3Reps != null && other.set3Reps != null && !this.set3Reps.equals(other.set3Reps))
            return false;
        if (this.set3Weight != null && other.set3Weight != null && !this.set3Weight.equals(other.set3Weight))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.set1Reps == null) ? 0 : this.set1Reps.hashCode());
        result = prime * result + ((this.set1Weight == null) ? 0 : this.set1Weight.hashCode());
        result = prime * result + ((this.set2Reps == null) ? 0 : this.set2Reps.hashCode());
        result = prime * result + ((this.set2Weight == null) ? 0 : this.set2Weight.hashCode());
        result = prime * result + ((this.set3Reps == null) ? 0 : this.set3Reps.hashCode());
        result = prime * result + ((this.set3Weight == null) ? 0 : this.set3Weight.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "UpdateWorkoutRequestExercises[" + "this.id=" + this.id + ", this.set1Reps=" + this.set1Reps + ", this.set1Weight=" + this.set1Weight + ", this.set2Reps=" + this.set2Reps
                + ", this.set2Weight=" + this.set2Weight + ", this.set3Reps=" + this.set3Reps + ", this.set3Weight=" + this.set3Weight + "]";
    }

}
