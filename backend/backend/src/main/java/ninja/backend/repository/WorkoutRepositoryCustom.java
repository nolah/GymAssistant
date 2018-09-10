package ninja.backend.repository;

import java.math.BigDecimal;
import java.time.*;
import java.util.List;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;
import ninja.backend.repository.tuple.*;


public interface WorkoutRepositoryCustom {

    List<Workout> findByWorkoutPlan(Long workoutPlanId);

    List<Workout> findByDate(ZonedDateTime date);

    List<Workout> findBySet1Reps(Integer set1Reps);

    List<Workout> findBySet1Weight(BigDecimal set1Weight);

    List<Workout> findBySet2Reps(Integer set2Reps);

    List<Workout> findBySet2Weight(BigDecimal set2Weight);

    List<Workout> findBySet3Reps(Integer set3Reps);

    List<Workout> findBySet3Weight(BigDecimal set3Weight);

    List<WorkoutWorkoutsTuple> workouts(Long userId);

}
