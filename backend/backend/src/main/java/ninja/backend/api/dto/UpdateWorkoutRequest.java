package ninja.backend.api.dto;

import java.io.Serializable;

import java.util.List;
import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.constraints.*;

import eu.execom.fabut.property.PropertyPath;


public class UpdateWorkoutRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<Long> ID = new PropertyPath<>("id");
    public static final PropertyPath<List<UpdateWorkoutRequestExercises>> EXERCISES = new PropertyPath<>("exercises");

    @NotNull
    private Long id;

    @Valid
    private List<UpdateWorkoutRequestExercises> exercises = new ArrayList<>();

    private UpdateWorkoutRequest() {
    }

    public UpdateWorkoutRequest(Long id, List<UpdateWorkoutRequestExercises> exercises) {
        this.id = id;
        this.exercises = exercises;
    }

    public Long getId() {
        return id;
    }

    public List<UpdateWorkoutRequestExercises> getExercises() {
        return exercises;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final UpdateWorkoutRequest other = (UpdateWorkoutRequest) obj;
        if (this.id != null && other.id != null && !this.id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "UpdateWorkoutRequest[" + "this.id=" + this.id + ", this.exercises=" + this.exercises + "]";
    }

}
