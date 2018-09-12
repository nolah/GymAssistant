package ninja.backend.repository.impl;

import java.math.BigDecimal;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;
import ninja.backend.repository.tuple.*;
import ninja.backend.repository.ExerciseRepositoryCustom;

import com.querydsl.jpa.JPQLQueryFactory;


public class ExerciseRepositoryImpl implements ExerciseRepositoryCustom {

    private final Logger log = LoggerFactory.getLogger(ExerciseRepositoryImpl.class);

    @Inject
    private JPQLQueryFactory factory;

    @Override
    public List<Exercise> findByWorkout(Long workoutId) {
        log.trace(".findByWorkout(workoutId: {})", workoutId);
        final QExercise exercise = QExercise.exercise;
        return factory.select(exercise).from(exercise).where(exercise.workout.id.eq(workoutId)).fetch();
    }

    @Override
    public List<Exercise> findByName(String name) {
        log.trace(".findByName(name: {})", name);
        final QExercise exercise = QExercise.exercise;
        return factory.select(exercise).from(exercise).where(exercise.name.eq(name)).fetch();
    }

    @Override
    public List<Exercise> findByGoalReps(Integer goalReps) {
        log.trace(".findByGoalReps(goalReps: {})", goalReps);
        final QExercise exercise = QExercise.exercise;
        return factory.select(exercise).from(exercise).where(exercise.goalReps.eq(goalReps)).fetch();
    }

    @Override
    public List<Exercise> findByGoalWeight(BigDecimal goalWeight) {
        log.trace(".findByGoalWeight(goalWeight: {})", goalWeight);
        final QExercise exercise = QExercise.exercise;
        return factory.select(exercise).from(exercise).where(exercise.goalWeight.eq(goalWeight)).fetch();
    }

    @Override
    public List<Exercise> findBySet1Reps(Integer set1Reps) {
        log.trace(".findBySet1Reps(set1Reps: {})", set1Reps);
        final QExercise exercise = QExercise.exercise;
        return factory.select(exercise).from(exercise).where(exercise.set1Reps.eq(set1Reps)).fetch();
    }

    @Override
    public List<Exercise> findBySet1Weight(BigDecimal set1Weight) {
        log.trace(".findBySet1Weight(set1Weight: {})", set1Weight);
        final QExercise exercise = QExercise.exercise;
        return factory.select(exercise).from(exercise).where(exercise.set1Weight.eq(set1Weight)).fetch();
    }

    @Override
    public List<Exercise> findBySet2Reps(Integer set2Reps) {
        log.trace(".findBySet2Reps(set2Reps: {})", set2Reps);
        final QExercise exercise = QExercise.exercise;
        return factory.select(exercise).from(exercise).where(exercise.set2Reps.eq(set2Reps)).fetch();
    }

    @Override
    public List<Exercise> findBySet2Weight(BigDecimal set2Weight) {
        log.trace(".findBySet2Weight(set2Weight: {})", set2Weight);
        final QExercise exercise = QExercise.exercise;
        return factory.select(exercise).from(exercise).where(exercise.set2Weight.eq(set2Weight)).fetch();
    }

    @Override
    public List<Exercise> findBySet3Reps(Integer set3Reps) {
        log.trace(".findBySet3Reps(set3Reps: {})", set3Reps);
        final QExercise exercise = QExercise.exercise;
        return factory.select(exercise).from(exercise).where(exercise.set3Reps.eq(set3Reps)).fetch();
    }

    @Override
    public List<Exercise> findBySet3Weight(BigDecimal set3Weight) {
        log.trace(".findBySet3Weight(set3Weight: {})", set3Weight);
        final QExercise exercise = QExercise.exercise;
        return factory.select(exercise).from(exercise).where(exercise.set3Weight.eq(set3Weight)).fetch();
    }

}
