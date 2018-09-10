package ninja.backend.repository.impl;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;
import ninja.backend.repository.tuple.*;
import ninja.backend.repository.WorkoutRepositoryCustom;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQueryFactory;


public class WorkoutRepositoryImpl implements WorkoutRepositoryCustom {

    private final Logger log = LoggerFactory.getLogger(WorkoutRepositoryImpl.class);

    @Inject
    private JPQLQueryFactory factory;

    @Override
    public List<Workout> findByWorkoutPlan(Long workoutPlanId) {
        log.trace(".findByWorkoutPlan(workoutPlanId: {})", workoutPlanId);
        final QWorkout workout = QWorkout.workout;
        return factory.select(workout).from(workout).where(workout.workoutPlan.id.eq(workoutPlanId)).fetch();
    }

    @Override
    public List<Workout> findByDate(ZonedDateTime date) {
        log.trace(".findByDate(date: {})", date);
        final QWorkout workout = QWorkout.workout;
        return factory.select(workout).from(workout).where(workout.date.eq(date)).fetch();
    }

    @Override
    public List<Workout> findBySet1Reps(Integer set1Reps) {
        log.trace(".findBySet1Reps(set1Reps: {})", set1Reps);
        final QWorkout workout = QWorkout.workout;
        return factory.select(workout).from(workout).where(workout.set1Reps.eq(set1Reps)).fetch();
    }

    @Override
    public List<Workout> findBySet1Weight(BigDecimal set1Weight) {
        log.trace(".findBySet1Weight(set1Weight: {})", set1Weight);
        final QWorkout workout = QWorkout.workout;
        return factory.select(workout).from(workout).where(workout.set1Weight.eq(set1Weight)).fetch();
    }

    @Override
    public List<Workout> findBySet2Reps(Integer set2Reps) {
        log.trace(".findBySet2Reps(set2Reps: {})", set2Reps);
        final QWorkout workout = QWorkout.workout;
        return factory.select(workout).from(workout).where(workout.set2Reps.eq(set2Reps)).fetch();
    }

    @Override
    public List<Workout> findBySet2Weight(BigDecimal set2Weight) {
        log.trace(".findBySet2Weight(set2Weight: {})", set2Weight);
        final QWorkout workout = QWorkout.workout;
        return factory.select(workout).from(workout).where(workout.set2Weight.eq(set2Weight)).fetch();
    }

    @Override
    public List<Workout> findBySet3Reps(Integer set3Reps) {
        log.trace(".findBySet3Reps(set3Reps: {})", set3Reps);
        final QWorkout workout = QWorkout.workout;
        return factory.select(workout).from(workout).where(workout.set3Reps.eq(set3Reps)).fetch();
    }

    @Override
    public List<Workout> findBySet3Weight(BigDecimal set3Weight) {
        log.trace(".findBySet3Weight(set3Weight: {})", set3Weight);
        final QWorkout workout = QWorkout.workout;
        return factory.select(workout).from(workout).where(workout.set3Weight.eq(set3Weight)).fetch();
    }

    @Override
    public List<WorkoutWorkoutsTuple> workouts(Long userId) {
        log.trace(".workouts(userId: {})", userId);
        final QWorkout workout = QWorkout.workout;
        final QWorkoutPlan workoutPlan = QWorkoutPlan.workoutPlan;
        return factory.select(workout, workoutPlan).from(workout).innerJoin(workout.workoutPlan, workoutPlan)
                .where(new BooleanBuilder().and(workoutPlan.active.eq(true)).and(workoutPlan.user.id.eq(userId))).fetch().stream()
                .map(t -> new WorkoutWorkoutsTuple(t.get(workout), t.get(workoutPlan))).collect(Collectors.toList());
    }

}
