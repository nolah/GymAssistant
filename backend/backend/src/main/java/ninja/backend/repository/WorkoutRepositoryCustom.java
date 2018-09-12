package ninja.backend.repository;

import java.time.*;
import java.util.List;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;
import ninja.backend.repository.tuple.*;


public interface WorkoutRepositoryCustom {

    List<WorkoutWorkoutsTuple> workouts(Long userId);

    List<Workout> findByWorkoutPlan(Long workoutPlanId);

    List<Workout> findByDate(ZonedDateTime date);

    List<Workout> findByName(String name);

}
