package ninja.backend.repository.tuple;

import ninja.backend.model.*;


public class WorkoutWorkoutsTuple {

    private final Workout workout;
    private final WorkoutPlan workoutPlan;
    private final Exercise exercise;

    public WorkoutWorkoutsTuple(Workout workout, WorkoutPlan workoutPlan, Exercise exercise) {
        this.workout = workout;
        this.workoutPlan = workoutPlan;
        this.exercise = exercise;
    }

    public Workout getWorkout() {
        return workout;
    }

    public WorkoutPlan getWorkoutPlan() {
        return workoutPlan;
    }

    public Exercise getExercise() {
        return exercise;
    }

}