package org.ninja.gymassistant.dto;


import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class UpdateWorkoutRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id;

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
