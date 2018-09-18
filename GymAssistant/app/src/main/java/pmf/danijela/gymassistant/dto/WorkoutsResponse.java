package pmf.danijela.gymassistant.dto;

import android.support.annotation.Size;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

import java.util.List;
import java.util.ArrayList;



public class WorkoutsResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    private Boolean active;

    private List<WorkoutsResponseWorkouts> workouts = new ArrayList<>();

    private WorkoutsResponse() {
    }

    public WorkoutsResponse(Long id, String name, Boolean active, List<WorkoutsResponseWorkouts> workouts) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.workouts = workouts;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getActive() {
        return active;
    }

    public List<WorkoutsResponseWorkouts> getWorkouts() {
        return workouts;
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
        if (this.name != null && other.name != null && !this.name.equals(other.name))
            return false;
        if (this.active != null && other.active != null && !this.active.equals(other.active))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.active == null) ? 0 : this.active.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "WorkoutsResponse[" + "this.id=" + this.id + ", this.name=" + this.name + ", this.active=" + this.active + ", this.workouts=" + this.workouts + "]";
    }

}
