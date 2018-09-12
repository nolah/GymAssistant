package ninja.backend.repository;

import java.math.BigDecimal;

import java.util.List;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;
import ninja.backend.repository.tuple.*;


public interface ExerciseRepositoryCustom {

    List<Exercise> findByWorkout(Long workoutId);

    List<Exercise> findByName(String name);

    List<Exercise> findByGoalReps(Integer goalReps);

    List<Exercise> findByGoalWeight(BigDecimal goalWeight);

    List<Exercise> findBySet1Reps(Integer set1Reps);

    List<Exercise> findBySet1Weight(BigDecimal set1Weight);

    List<Exercise> findBySet2Reps(Integer set2Reps);

    List<Exercise> findBySet2Weight(BigDecimal set2Weight);

    List<Exercise> findBySet3Reps(Integer set3Reps);

    List<Exercise> findBySet3Weight(BigDecimal set3Weight);

}
