package ninja.backend.repository.impl;

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
    public List<WorkoutWorkoutsTuple> workouts(Long principalUser) {
        log.trace(".workouts(principalUser: {})", principalUser);
        final QWorkout workout = QWorkout.workout;
        final QWorkoutPlan workoutPlan = QWorkoutPlan.workoutPlan;
        final QExercise exercise = QExercise.exercise;
        return factory.select(workout, workoutPlan, exercise).from(workout).innerJoin(workout.workoutPlan, workoutPlan).innerJoin(workout.exercises, exercise)
                .where(new BooleanBuilder().and(workoutPlan.active.eq(true)).and(workoutPlan.user.id.eq(principalUser))).fetch().stream()
                .map(t -> new WorkoutWorkoutsTuple(t.get(workout), t.get(workoutPlan), t.get(exercise))).collect(Collectors.toList());
    }

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
    public List<Workout> findByName(String name) {
        log.trace(".findByName(name: {})", name);
        final QWorkout workout = QWorkout.workout;
        return factory.select(workout).from(workout).where(workout.name.eq(name)).fetch();
    }

}
