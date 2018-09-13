package ninja.backend.repository.impl;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;
import ninja.backend.repository.tuple.*;
import ninja.backend.repository.WorkoutPlanRepositoryCustom;

import com.querydsl.jpa.JPQLQueryFactory;


public class WorkoutPlanRepositoryImpl implements WorkoutPlanRepositoryCustom {

    private final Logger log = LoggerFactory.getLogger(WorkoutPlanRepositoryImpl.class);

    @Inject
    private JPQLQueryFactory factory;

    @Override
    public List<WorkoutPlan> findByUser(Long userId) {
        log.trace(".findByUser(userId: {})", userId);
        final QWorkoutPlan workoutPlan = QWorkoutPlan.workoutPlan;
        return factory.select(workoutPlan).from(workoutPlan).where(workoutPlan.user.id.eq(userId)).fetch();
    }

    @Override
    public List<WorkoutPlan> findByName(String name) {
        log.trace(".findByName(name: {})", name);
        final QWorkoutPlan workoutPlan = QWorkoutPlan.workoutPlan;
        return factory.select(workoutPlan).from(workoutPlan).where(workoutPlan.name.eq(name)).fetch();
    }

    @Override
    public List<WorkoutPlan> findByActive(Boolean active) {
        log.trace(".findByActive(active: {})", active);
        final QWorkoutPlan workoutPlan = QWorkoutPlan.workoutPlan;
        return factory.select(workoutPlan).from(workoutPlan).where(workoutPlan.active.eq(active)).fetch();
    }

    @Override
    public List<WorkoutPlan> workoutPlans(Long principalUser) {
        log.trace(".workoutPlans(principalUser: {})", principalUser);
        final QWorkoutPlan workoutPlan = QWorkoutPlan.workoutPlan;
        return factory.select(workoutPlan).from(workoutPlan).where(workoutPlan.user.id.eq(principalUser)).fetch();
    }

}
