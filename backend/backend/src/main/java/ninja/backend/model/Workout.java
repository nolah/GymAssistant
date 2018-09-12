package ninja.backend.model;

import java.io.Serializable;

import java.time.*;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import eu.execom.fabut.property.PropertyPath;
import ninja.backend.model.enumeration.*;


@Entity
@Table(name = "Workout")
public class Workout implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<Long> ID = new PropertyPath<>("id");
    public static final PropertyPath<WorkoutPlan> WORKOUT_PLAN = new PropertyPath<>("workoutPlan");
    public static final PropertyPath<ZonedDateTime> DATE = new PropertyPath<>("date");
    public static final PropertyPath<String> NAME = new PropertyPath<>("name");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "workoutPlanId")
    private WorkoutPlan workoutPlan;

    @NotNull
    @Column(name = "date")
    private ZonedDateTime date;

    @NotNull
    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @NotNull
    @OneToMany(mappedBy = "workout", cascade = CascadeType.PERSIST)
    private final Set<Exercise> exercises = new LinkedHashSet<>();

    public Workout() {
    }

    public Workout(Long id, WorkoutPlan workoutPlan, ZonedDateTime date, String name) {
        this.id = id;
        this.workoutPlan = workoutPlan;
        this.date = date;
        this.name = name;
    }

    public Workout(WorkoutPlan workoutPlan, ZonedDateTime date, String name) {
        this.workoutPlan = workoutPlan;
        this.date = date;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WorkoutPlan getWorkoutPlan() {
        return workoutPlan;
    }

    public void setWorkoutPlan(WorkoutPlan workoutPlan) {
        this.workoutPlan = workoutPlan;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Exercise> getExercises() {
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
        final Workout other = (Workout) obj;
        if (this.id != null && other.id != null && !this.id.equals(other.id))
            return false;
        if (!((this.workoutPlan == null && other.workoutPlan == null)
                || (this.workoutPlan != null && other.workoutPlan != null && this.workoutPlan.getId() == null && other.workoutPlan.getId() == null)
                || this.workoutPlan.getId().equals(other.workoutPlan.getId())))
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
        result = prime * result + ((this.workoutPlan == null || this.workoutPlan.getId() == null) ? 0 : this.workoutPlan.getId().hashCode());
        result = prime * result + ((this.date == null) ? 0 : this.date.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Workout[" + "this.id=" + this.id + ", this.workoutPlan=" + (this.workoutPlan == null ? this.workoutPlan : this.workoutPlan.getId()) + ", this.date=" + this.date + ", this.name="
                + this.name + "]";
    }

}
