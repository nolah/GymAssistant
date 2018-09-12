package ninja.backend.api.dto;

import java.io.Serializable;

import javax.validation.constraints.*;

import eu.execom.fabut.property.PropertyPath;


public class WorkoutPlansRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<Long> USER_ID = new PropertyPath<>("userId");

    @NotNull
    private Long userId;

    private WorkoutPlansRequest() {
    }

    public WorkoutPlansRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final WorkoutPlansRequest other = (WorkoutPlansRequest) obj;
        if (this.userId != null && other.userId != null && !this.userId.equals(other.userId))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "WorkoutPlansRequest[" + "this.userId=" + this.userId + "]";
    }

}
