package ninja.backend.api.dto;

import java.io.Serializable;

import java.math.BigDecimal;
import java.time.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;

import eu.execom.fabut.property.PropertyPath;


public class WorkoutsResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<Long> ID = new PropertyPath<>("id");
    public static final PropertyPath<Long> WORKOUT_PLAN_ID = new PropertyPath<>("workoutPlanId");
    public static final PropertyPath<ZonedDateTime> DATE = new PropertyPath<>("date");
    public static final PropertyPath<Integer> SET_1_REPS = new PropertyPath<>("set1Reps");
    public static final PropertyPath<BigDecimal> SET_1_WEIGHT = new PropertyPath<>("set1Weight");
    public static final PropertyPath<Integer> SET_2_REPS = new PropertyPath<>("set2Reps");
    public static final PropertyPath<BigDecimal> SET_2_WEIGHT = new PropertyPath<>("set2Weight");
    public static final PropertyPath<Integer> SET_3_REPS = new PropertyPath<>("set3Reps");
    public static final PropertyPath<BigDecimal> SET_3_WEIGHT = new PropertyPath<>("set3Weight");
    public static final PropertyPath<Long> PLAN_ID = new PropertyPath<>("planId");
    public static final PropertyPath<Long> PLAN_USER_ID = new PropertyPath<>("planUserId");
    public static final PropertyPath<Boolean> PLAN_ACTIVE = new PropertyPath<>("planActive");

    @NotNull
    private Long id;

    @NotNull
    private Long workoutPlanId;

    @NotNull
    private ZonedDateTime date;

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

    @NotNull
    private Long planId;

    @NotNull
    private Long planUserId;

    @NotNull
    private Boolean planActive;

    private WorkoutsResponse() {
    }

    public WorkoutsResponse(Long id, Long workoutPlanId, ZonedDateTime date, Integer set1Reps, BigDecimal set1Weight, Integer set2Reps, BigDecimal set2Weight, Integer set3Reps, BigDecimal set3Weight,
            Long planId, Long planUserId, Boolean planActive) {
        this.id = id;
        this.workoutPlanId = workoutPlanId;
        this.date = date;
        this.set1Reps = set1Reps;
        this.set1Weight = set1Weight;
        this.set2Reps = set2Reps;
        this.set2Weight = set2Weight;
        this.set3Reps = set3Reps;
        this.set3Weight = set3Weight;
        this.planId = planId;
        this.planUserId = planUserId;
        this.planActive = planActive;
    }

    public Long getId() {
        return id;
    }

    public Long getWorkoutPlanId() {
        return workoutPlanId;
    }

    public ZonedDateTime getDate() {
        return date;
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

    public Long getPlanId() {
        return planId;
    }

    public Long getPlanUserId() {
        return planUserId;
    }

    public Boolean getPlanActive() {
        return planActive;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final WorkoutsResponse other = (WorkoutsResponse) obj;
        if (this.id != null && other.id != null && !this.id.equals(other.id))
            return false;
        if (this.workoutPlanId != null && other.workoutPlanId != null && !this.workoutPlanId.equals(other.workoutPlanId))
            return false;
        if (this.date != null && other.date != null && !this.date.equals(other.date))
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
        if (this.planId != null && other.planId != null && !this.planId.equals(other.planId))
            return false;
        if (this.planUserId != null && other.planUserId != null && !this.planUserId.equals(other.planUserId))
            return false;
        if (this.planActive != null && other.planActive != null && !this.planActive.equals(other.planActive))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.workoutPlanId == null) ? 0 : this.workoutPlanId.hashCode());
        result = prime * result + ((this.date == null) ? 0 : this.date.hashCode());
        result = prime * result + ((this.set1Reps == null) ? 0 : this.set1Reps.hashCode());
        result = prime * result + ((this.set1Weight == null) ? 0 : this.set1Weight.hashCode());
        result = prime * result + ((this.set2Reps == null) ? 0 : this.set2Reps.hashCode());
        result = prime * result + ((this.set2Weight == null) ? 0 : this.set2Weight.hashCode());
        result = prime * result + ((this.set3Reps == null) ? 0 : this.set3Reps.hashCode());
        result = prime * result + ((this.set3Weight == null) ? 0 : this.set3Weight.hashCode());
        result = prime * result + ((this.planId == null) ? 0 : this.planId.hashCode());
        result = prime * result + ((this.planUserId == null) ? 0 : this.planUserId.hashCode());
        result = prime * result + ((this.planActive == null) ? 0 : this.planActive.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "WorkoutsResponse[" + "this.id=" + this.id + ", this.workoutPlanId=" + this.workoutPlanId + ", this.date=" + this.date + ", this.set1Reps=" + this.set1Reps + ", this.set1Weight="
                + this.set1Weight + ", this.set2Reps=" + this.set2Reps + ", this.set2Weight=" + this.set2Weight + ", this.set3Reps=" + this.set3Reps + ", this.set3Weight=" + this.set3Weight
                + ", this.planId=" + this.planId + ", this.planUserId=" + this.planUserId + ", this.planActive=" + this.planActive + "]";
    }

}
