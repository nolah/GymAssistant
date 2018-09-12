package ninja.backend.api;

import java.time.*;

import javax.inject.Inject;

import org.slf4j.*;

import ninja.backend.model.*;
import ninja.backend.repository.*;
import ninja.backend.api.dto.*;

import java.util.*;
import java.util.stream.*;
import ninja.backend.model.enumeration.*;
import java.math.BigDecimal;

import ninja.backend.repository.tuple.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class WorkoutApi {

    private final Logger log = LoggerFactory.getLogger(WorkoutApi.class);

    @Inject
    private UserRepository userRepository;

    @Inject
    private WorkoutPlanRepository workoutPlanRepository;

    @Inject
    private WorkoutRepository workoutRepository;

    @Inject
    private ExerciseRepository exerciseRepository;

    public void startWorkoutPlan(StartWorkoutPlanRequest dto, Long principalId) {
        log.debug("startWorkoutPlan {} {}", dto, principalId);
        //TODO check security constraints

        final User principal = userRepository.findOne(principalId);

        //TODO process event
        throw new UnsupportedOperationException();
    }

    @Transactional(readOnly = true)
    public List<WorkoutPlansResponse> workoutPlans(WorkoutPlansRequest dto, Long principalId) {
        log.debug("workoutPlans {} {}", dto, principalId);
        //TODO check security constraints(userId)

        final User principal = userRepository.findOne(principalId);

        final List<WorkoutPlan> models = workoutPlanRepository.workoutPlans(dto.getUserId());
        return models.stream().map(model -> {
            final Long id = model.getId();
            final Long userId = model.getUser().getId();
            final String name = model.getName();
            final Boolean active = model.getActive();
            return new WorkoutPlansResponse(id, userId, name, active);
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public QuickInfoResponse quickInfo(Long principalId) {
        log.debug("quickInfo {}", principalId);
        //TODO check security constraints

        final User principal = userRepository.findOne(principalId);

        throw new UnsupportedOperationException();
    }

    @Transactional(readOnly = true)
    public WorkoutsResponse workouts(WorkoutsRequest dto, Long principalId) {
        log.debug("workouts {} {}", dto, principalId);
        //TODO check security constraints(id)

        final User principal = userRepository.findOne(principalId);

        throw new UnsupportedOperationException();
    }

    public void updateWorkout(UpdateWorkoutRequest dto, Long principalId) {
        log.debug("updateWorkout {} {}", dto, principalId);
        //TODO check security constraints(id)

        final User principal = userRepository.findOne(principalId);

        final Workout model = workoutRepository.findOne(dto.getId());
        final Set<Exercise> exercises = dto.getExercises().stream().map(updateWorkoutRequestExercises -> {
            final Exercise exercise = convertToExercise(updateWorkoutRequestExercises);
            exercise.setWorkout(model);
            return exercise;
        }).collect(Collectors.toSet());
        model.getExercises().clear();
        model.getExercises().addAll(exercises);
        workoutRepository.save(model);
        //TODO process event

    }

    private WorkoutsResponseWorkouts convertToWorkoutsResponseWorkouts(Workout model) {
        final Long id = model.getId();
        final ZonedDateTime date = model.getDate();
        final String name = model.getName();
        final List<WorkoutsResponseWorkoutsExercises> exercises = model.getExercises().stream().map(this::convertToWorkoutsResponseWorkoutsExercises).collect(Collectors.toList());
        return new WorkoutsResponseWorkouts(id, date, name, exercises);
    }

    private WorkoutsResponseWorkoutsExercises convertToWorkoutsResponseWorkoutsExercises(Exercise model) {
        final String name = model.getName();
        final Integer goalReps = model.getGoalReps();
        final BigDecimal goalWeight = model.getGoalWeight();
        final Integer set1Reps = model.getSet1Reps();
        final BigDecimal set1Weight = model.getSet1Weight();
        final Integer set2Reps = model.getSet2Reps();
        final BigDecimal set2Weight = model.getSet2Weight();
        final Integer set3Reps = model.getSet3Reps();
        final BigDecimal set3Weight = model.getSet3Weight();
        return new WorkoutsResponseWorkoutsExercises(name, goalReps, goalWeight, set1Reps, set1Weight, set2Reps, set2Weight, set3Reps, set3Weight);
    }

    private Exercise convertToExercise(UpdateWorkoutRequestExercises dto) {
        final Exercise model = new Exercise();
        model.setSet1Reps(dto.getSet1Reps());
        model.setSet1Weight(dto.getSet1Weight());
        model.setSet2Reps(dto.getSet2Reps());
        model.setSet2Weight(dto.getSet2Weight());
        model.setSet3Reps(dto.getSet3Reps());
        model.setSet3Weight(dto.getSet3Weight());
        return model;
    }
}
