package ninja.backend.model;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import eu.execom.fabut.property.PropertyPath;
import ninja.backend.model.enumeration.*;


@Entity
@Table(name = "Exercise")
public class Exercise implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<Long> ID = new PropertyPath<>("id");
    public static final PropertyPath<Workout> WORKOUT = new PropertyPath<>("workout");
    public static final PropertyPath<String> NAME = new PropertyPath<>("name");
    public static final PropertyPath<Integer> GOAL_REPS = new PropertyPath<>("goalReps");
    public static final PropertyPath<BigDecimal> GOAL_WEIGHT = new PropertyPath<>("goalWeight");
    public static final PropertyPath<Integer> SET_1_REPS = new PropertyPath<>("set1Reps");
    public static final PropertyPath<BigDecimal> SET_1_WEIGHT = new PropertyPath<>("set1Weight");
    public static final PropertyPath<Integer> SET_2_REPS = new PropertyPath<>("set2Reps");
    public static final PropertyPath<BigDecimal> SET_2_WEIGHT = new PropertyPath<>("set2Weight");
    public static final PropertyPath<Integer> SET_3_REPS = new PropertyPath<>("set3Reps");
    public static final PropertyPath<BigDecimal> SET_3_WEIGHT = new PropertyPath<>("set3Weight");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "workoutId")
    private Workout workout;

    @NotNull
    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "goalReps")
    private Integer goalReps;

    @NotNull
    @Column(name = "goalWeight")
    private BigDecimal goalWeight;

    @NotNull
    @Column(name = "set1Reps")
    private Integer set1Reps;

    @NotNull
    @Column(name = "set1Weight")
    private BigDecimal set1Weight;

    @NotNull
    @Column(name = "set2Reps")
    private Integer set2Reps;

    @NotNull
    @Column(name = "set2Weight")
    private BigDecimal set2Weight;

    @NotNull
    @Column(name = "set3Reps")
    private Integer set3Reps;

    @NotNull
    @Column(name = "set3Weight")
    private BigDecimal set3Weight;

    public Exercise() {
    }

    public Exercise(Long id, Workout workout, String name, Integer goalReps, BigDecimal goalWeight, Integer set1Reps, BigDecimal set1Weight, Integer set2Reps, BigDecimal set2Weight, Integer set3Reps,
            BigDecimal set3Weight) {
        this.id = id;
        this.workout = workout;
        this.name = name;
        this.goalReps = goalReps;
        this.goalWeight = goalWeight;
        this.set1Reps = set1Reps;
        this.set1Weight = set1Weight;
        this.set2Reps = set2Reps;
        this.set2Weight = set2Weight;
        this.set3Reps = set3Reps;
        this.set3Weight = set3Weight;
    }

    public Exercise(Workout workout, String name, Integer goalReps, BigDecimal goalWeight, Integer set1Reps, BigDecimal set1Weight, Integer set2Reps, BigDecimal set2Weight, Integer set3Reps,
            BigDecimal set3Weight) {
        this.workout = workout;
        this.name = name;
        this.goalReps = goalReps;
        this.goalWeight = goalWeight;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGoalReps() {
        return goalReps;
    }

    public void setGoalReps(Integer goalReps) {
        this.goalReps = goalReps;
    }

    public BigDecimal getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(BigDecimal goalWeight) {
        this.goalWeight = goalWeight;
    }

    public Integer getSet1Reps() {
        return set1Reps;
    }

    public void setSet1Reps(Integer set1Reps) {
        this.set1Reps = set1Reps;
    }

    public BigDecimal getSet1Weight() {
        return set1Weight;
    }

    public void setSet1Weight(BigDecimal set1Weight) {
        this.set1Weight = set1Weight;
    }

    public Integer getSet2Reps() {
        return set2Reps;
    }

    public void setSet2Reps(Integer set2Reps) {
        this.set2Reps = set2Reps;
    }

    public BigDecimal getSet2Weight() {
        return set2Weight;
    }

    public void setSet2Weight(BigDecimal set2Weight) {
        this.set2Weight = set2Weight;
    }

    public Integer getSet3Reps() {
        return set3Reps;
    }

    public void setSet3Reps(Integer set3Reps) {
        this.set3Reps = set3Reps;
    }

    public BigDecimal getSet3Weight() {
        return set3Weight;
    }

    public void setSet3Weight(BigDecimal set3Weight) {
        this.set3Weight = set3Weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Exercise other = (Exercise) obj;
        if (this.id != null && other.id != null && !this.id.equals(other.id))
            return false;
        if (!((this.workout == null && other.workout == null) || (this.workout != null && other.workout != null && this.workout.getId() == null && other.workout.getId() == null)
                || this.workout.getId().equals(other.workout.getId())))
            return false;

        if (this.name != null && other.name != null && !this.name.equals(other.name))
            return false;
        if (this.goalReps != null && other.goalReps != null && !this.goalReps.equals(other.goalReps))
            return false;
        if (this.goalWeight != null && other.goalWeight != null && !this.goalWeight.equals(other.goalWeight))
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
        result = prime * result + ((this.workout == null || this.workout.getId() == null) ? 0 : this.workout.getId().hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.goalReps == null) ? 0 : this.goalReps.hashCode());
        result = prime * result + ((this.goalWeight == null) ? 0 : this.goalWeight.hashCode());
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
        return "Exercise[" + "this.id=" + this.id + ", this.workout=" + (this.workout == null ? this.workout : this.workout.getId()) + ", this.name=" + this.name + ", this.goalReps=" + this.goalReps
                + ", this.goalWeight=" + this.goalWeight + ", this.set1Reps=" + this.set1Reps + ", this.set1Weight=" + this.set1Weight + ", this.set2Reps=" + this.set2Reps + ", this.set2Weight="
                + this.set2Weight + ", this.set3Reps=" + this.set3Reps + ", this.set3Weight=" + this.set3Weight + "]";
    }

}
