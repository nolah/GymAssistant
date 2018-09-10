package ninja.backend.repository.tuple;

import ninja.backend.model.*;


public class WorkoutWorkoutsTuple {

    private final Workout workout;
    private final WorkoutPlan workoutPlan;

    public WorkoutWorkoutsTuple(Workout workout, WorkoutPlan workoutPlan) {
        this.workout = workout;
        this.workoutPlan = workoutPlan;
    }

    public Workout getWorkout() {
        return workout;
    }

    public WorkoutPlan getWorkoutPlan() {
        return workoutPlan;
    }

}