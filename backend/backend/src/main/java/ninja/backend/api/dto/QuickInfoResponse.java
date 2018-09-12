package ninja.backend.api.dto;

import java.io.Serializable;

import javax.validation.constraints.*;

import eu.execom.fabut.property.PropertyPath;


public class QuickInfoResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<Long> ACTIVE_WORKOUT_PLAN_ID = new PropertyPath<>("activeWorkoutPlanId");
    public static final PropertyPath<String> ACTIVE_WORKOUT_PLAN_NAME = new PropertyPath<>("activeWorkoutPlanName");
    public static final PropertyPath<Long> TODAYS_WORKOUT_ID = new PropertyPath<>("todaysWorkoutId");
    public static final PropertyPath<String> TODAYS_WORKOUT_NAME = new PropertyPath<>("todaysWorkoutName");

    private Long activeWorkoutPlanId;

    @Size(max = 255)
    private String activeWorkoutPlanName;

    private Long todaysWorkoutId;

    @Size(max = 255)
    private String todaysWorkoutName;

    private QuickInfoResponse() {
    }

    public QuickInfoResponse(Long activeWorkoutPlanId, String activeWorkoutPlanName, Long todaysWorkoutId, String todaysWorkoutName) {
        this.activeWorkoutPlanId = activeWorkoutPlanId;
        this.activeWorkoutPlanName = activeWorkoutPlanName;
        this.todaysWorkoutId = todaysWorkoutId;
        this.todaysWorkoutName = todaysWorkoutName;
    }

    public Long getActiveWorkoutPlanId() {
        return activeWorkoutPlanId;
    }

    public String getActiveWorkoutPlanName() {
        return activeWorkoutPlanName;
    }

    public Long getTodaysWorkoutId() {
        return todaysWorkoutId;
    }

    public String getTodaysWorkoutName() {
        return todaysWorkoutName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final QuickInfoResponse other = (QuickInfoResponse) obj;
        if (this.activeWorkoutPlanId != null && other.activeWorkoutPlanId != null && !this.activeWorkoutPlanId.equals(other.activeWorkoutPlanId))
            return false;
        if (this.activeWorkoutPlanName != null && other.activeWorkoutPlanName != null && !this.activeWorkoutPlanName.equals(other.activeWorkoutPlanName))
            return false;
        if (this.todaysWorkoutId != null && other.todaysWorkoutId != null && !this.todaysWorkoutId.equals(other.todaysWorkoutId))
            return false;
        if (this.todaysWorkoutName != null && other.todaysWorkoutName != null && !this.todaysWorkoutName.equals(other.todaysWorkoutName))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.activeWorkoutPlanId == null) ? 0 : this.activeWorkoutPlanId.hashCode());
        result = prime * result + ((this.activeWorkoutPlanName == null) ? 0 : this.activeWorkoutPlanName.hashCode());
        result = prime * result + ((this.todaysWorkoutId == null) ? 0 : this.todaysWorkoutId.hashCode());
        result = prime * result + ((this.todaysWorkoutName == null) ? 0 : this.todaysWorkoutName.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "QuickInfoResponse[" + "this.activeWorkoutPlanId=" + this.activeWorkoutPlanId + ", this.activeWorkoutPlanName=" + this.activeWorkoutPlanName + ", this.todaysWorkoutId="
                + this.todaysWorkoutId + ", this.todaysWorkoutName=" + this.todaysWorkoutName + "]";
    }

}
