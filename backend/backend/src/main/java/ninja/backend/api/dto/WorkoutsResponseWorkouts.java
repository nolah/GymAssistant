package ninja.backend.api.dto;

import java.io.Serializable;

import java.time.*;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;

import eu.execom.fabut.property.PropertyPath;


public class WorkoutsResponseWorkouts implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<Long> ID = new PropertyPath<>("id");
    public static final PropertyPath<ZonedDateTime> DATE = new PropertyPath<>("date");
    public static final PropertyPath<String> NAME = new PropertyPath<>("name");
    public static final PropertyPath<List<WorkoutsResponseWorkoutsExercises>> EXERCISES = new PropertyPath<>("exercises");

    @NotNull
    private Long id;

    @NotNull
    private ZonedDateTime date;

    @NotNull
    @Size(max = 255)
    private String name;

    @Valid
    private List<WorkoutsResponseWorkoutsExercises> exercises = new ArrayList<>();

    private WorkoutsResponseWorkouts() {
    }

    public WorkoutsResponseWorkouts(Long id, ZonedDateTime date, String name, List<WorkoutsResponseWorkoutsExercises> exercises) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.exercises = exercises;
    }

    public Long getId() {
        return id;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public List<WorkoutsResponseWorkoutsExercises> getExercises() {
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
        final WorkoutsResponseWorkouts other = (WorkoutsResponseWorkouts) obj;
        if (this.id != null && other.id != null && !this.id.equals(other.id))
            return false;
        if (this.date != null && other.date != null && !this.date.equals(other.date))
            return false;
        if (this.name != null && other.name != null && !this.name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.date == null) ? 0 : this.date.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "WorkoutsResponseWorkouts[" + "this.id=" + this.id + ", this.date=" + this.date + ", this.name=" + this.name + ", this.exercises=" + this.exercises + "]";
    }

}
