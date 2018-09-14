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

import static java.util.Optional.empty;


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

        final User principal = userRepository.findOne(principalId);

        final List<WorkoutPlan> workoutPlans = workoutPlanRepository.findByUser(principalId);
        final Optional<WorkoutPlan> activeWorkoutPlan = workoutPlans.stream().filter(WorkoutPlan::getActive).findAny();
        activeWorkoutPlan.ifPresent(wp -> {
            wp.setActive(false);
            workoutPlanRepository.save(wp);
        });

        final WorkoutPlan newWorkoutPlan = new WorkoutPlan();
        newWorkoutPlan.setActive(true);
        newWorkoutPlan.setUser(principal);
        newWorkoutPlan.setName("Linear progression " + workoutPlans.size() + 1);
        workoutPlanRepository.save(newWorkoutPlan);

        ZonedDateTime monday = ZonedDateTime.now().with(DayOfWeek.MONDAY);
        ZonedDateTime tuesday = ZonedDateTime.now().with(DayOfWeek.TUESDAY);
        ZonedDateTime thursday = ZonedDateTime.now().with(DayOfWeek.THURSDAY);
        ZonedDateTime friday = ZonedDateTime.now().with(DayOfWeek.FRIDAY);

        final double maxSquat = dto.getMaxSquat().doubleValue();
        final double maxBench = dto.getMaxBench().doubleValue();
        final double maxDeadlift = dto.getMaxDeadlift().doubleValue();
        for (int i = 0; i <= 10; i++) {
            final Workout mondayWorkout = new Workout();
            mondayWorkout.setName("Squat day, week: " + (i + 1));
            mondayWorkout.setDate(monday);
            mondayWorkout.setWorkoutPlan(newWorkoutPlan);
            workoutRepository.save(mondayWorkout);

            final Exercise exercise1 = new Exercise();
            exercise1.setName("Back squat");
            exercise1.setGoalReps(5);
            exercise1.setGoalWeight(BigDecimal.valueOf(maxSquat * 0.75 + (i + 1) * maxSquat * 0.025));
            exercise1.setSet1Reps(0);
            exercise1.setSet1Weight(BigDecimal.ZERO);
            exercise1.setSet2Reps(0);
            exercise1.setSet2Weight(BigDecimal.ZERO);
            exercise1.setSet3Reps(0);
            exercise1.setSet3Weight(BigDecimal.ZERO);
            exercise1.setWorkout(mondayWorkout);
            exerciseRepository.save(exercise1);
            mondayWorkout.getExercises().add(exercise1);

            final Exercise exercise2 = new Exercise();
            exercise2.setName("Front squat");
            exercise2.setGoalReps(5);
            exercise2.setGoalWeight(BigDecimal.valueOf(maxSquat * 0.5 + (i + 1) * maxSquat * 0.025));
            exercise2.setSet1Reps(0);
            exercise2.setSet1Weight(BigDecimal.ZERO);
            exercise2.setSet2Reps(0);
            exercise2.setSet2Weight(BigDecimal.ZERO);
            exercise2.setSet3Reps(0);
            exercise2.setSet3Weight(BigDecimal.ZERO);
            exercise2.setWorkout(mondayWorkout);
            exerciseRepository.save(exercise2);
            mondayWorkout.getExercises().add(exercise2);

            newWorkoutPlan.getWorkouts().add(mondayWorkout);

            final Workout tuesdayWorkout = new Workout();
            tuesdayWorkout.setName("Bench day, week: " + (i + 1));
            tuesdayWorkout.setDate(tuesday);
            tuesdayWorkout.setWorkoutPlan(newWorkoutPlan);
            workoutRepository.save(tuesdayWorkout);

            final Exercise exercise3 = new Exercise();
            exercise3.setName("Bench press");
            exercise3.setGoalReps(5);
            exercise3.setGoalWeight(BigDecimal.valueOf(maxBench * 0.75 + (i + 1) * maxBench * 0.025));
            exercise3.setSet1Reps(0);
            exercise3.setSet1Weight(BigDecimal.ZERO);
            exercise3.setSet2Reps(0);
            exercise3.setSet2Weight(BigDecimal.ZERO);
            exercise3.setSet3Reps(0);
            exercise3.setSet3Weight(BigDecimal.ZERO);
            exercise3.setWorkout(tuesdayWorkout);
            exerciseRepository.save(exercise3);
            tuesdayWorkout.getExercises().add(exercise3);

            final Exercise exercise4 = new Exercise();
            exercise4.setName("Close grip bench press");
            exercise4.setGoalReps(5);
            exercise4.setGoalWeight(BigDecimal.valueOf(maxBench * 0.5 + (i + 1) * maxBench * 0.025));
            exercise4.setSet1Reps(0);
            exercise4.setSet1Weight(BigDecimal.ZERO);
            exercise4.setSet2Reps(0);
            exercise4.setSet2Weight(BigDecimal.ZERO);
            exercise4.setSet3Reps(0);
            exercise4.setSet3Weight(BigDecimal.ZERO);
            exercise4.setWorkout(tuesdayWorkout);
            exerciseRepository.save(exercise4);
            tuesdayWorkout.getExercises().add(exercise4);

            newWorkoutPlan.getWorkouts().add(tuesdayWorkout);

            final Workout thursdayWorkout = new Workout();
            thursdayWorkout.setName("Deadlift day, week: " + (i + 1));
            thursdayWorkout.setDate(thursday);
            thursdayWorkout.setWorkoutPlan(newWorkoutPlan);
            workoutRepository.save(thursdayWorkout);

            final Exercise exercise5 = new Exercise();
            exercise5.setName("Deadlift");
            exercise5.setGoalReps(5);
            exercise5.setGoalWeight(BigDecimal.valueOf(maxDeadlift * 0.75 + (i + 1) * maxDeadlift * 0.025));
            exercise5.setSet1Reps(0);
            exercise5.setSet1Weight(BigDecimal.ZERO);
            exercise5.setSet2Reps(0);
            exercise5.setSet2Weight(BigDecimal.ZERO);
            exercise5.setSet3Reps(0);
            exercise5.setSet3Weight(BigDecimal.ZERO);
            exercise5.setWorkout(thursdayWorkout);
            exerciseRepository.save(exercise5);
            thursdayWorkout.getExercises().add(exercise5);

            final Exercise exercise6 = new Exercise();
            exercise6.setName("Romanian deadlift");
            exercise6.setGoalReps(5);
            exercise6.setGoalWeight(BigDecimal.valueOf(maxDeadlift * 0.5 + (i + 1) * maxDeadlift * 0.025));
            exercise6.setSet1Reps(0);
            exercise6.setSet1Weight(BigDecimal.ZERO);
            exercise6.setSet2Reps(0);
            exercise6.setSet2Weight(BigDecimal.ZERO);
            exercise6.setSet3Reps(0);
            exercise6.setSet3Weight(BigDecimal.ZERO);
            exercise6.setWorkout(thursdayWorkout);
            exerciseRepository.save(exercise6);
            thursdayWorkout.getExercises().add(exercise6);

            newWorkoutPlan.getWorkouts().add(thursdayWorkout);

            final Workout fridayWorkout = new Workout();
            fridayWorkout.setName("Overhead press day, week: " + (i + 1));
            fridayWorkout.setDate(friday);
            fridayWorkout.setWorkoutPlan(newWorkoutPlan);
            workoutRepository.save(fridayWorkout);

            final Exercise exercise7 = new Exercise();
            exercise7.setName("Overhead press");
            exercise7.setGoalReps(5);
            exercise7.setGoalWeight(BigDecimal.valueOf(maxBench * 0.5 + (i + 1) * maxBench * 0.025));
            exercise7.setSet1Reps(0);
            exercise7.setSet1Weight(BigDecimal.ZERO);
            exercise7.setSet2Reps(0);
            exercise7.setSet2Weight(BigDecimal.ZERO);
            exercise7.setSet3Reps(0);
            exercise7.setSet3Weight(BigDecimal.ZERO);
            exercise7.setWorkout(fridayWorkout);
            exerciseRepository.save(exercise7);
            fridayWorkout.getExercises().add(exercise7);

            final Exercise exercise8 = new Exercise();
            exercise8.setName("Dumbbell flies");
            exercise8.setGoalReps(5);
            exercise8.setGoalWeight(BigDecimal.valueOf(maxBench * 0.2 + (i + 1) * maxBench * 0.01));
            exercise8.setSet1Reps(0);
            exercise8.setSet1Weight(BigDecimal.ZERO);
            exercise8.setSet2Reps(0);
            exercise8.setSet2Weight(BigDecimal.ZERO);
            exercise8.setSet3Reps(0);
            exercise8.setSet3Weight(BigDecimal.ZERO);
            exercise8.setWorkout(fridayWorkout);
            exerciseRepository.save(exercise8);
            fridayWorkout.getExercises().add(exercise8);

            newWorkoutPlan.getWorkouts().add(fridayWorkout);

            monday = monday.plusWeeks(1);
            tuesday = tuesday.plusWeeks(1);
            thursday = thursday.plusWeeks(1);
            friday = friday.plusWeeks(1);
        }

        workoutPlanRepository.save(newWorkoutPlan);

    }

    @Transactional(readOnly = true)
    public List<WorkoutPlansResponse> workoutPlans(Long principalId) {
        log.debug("workoutPlans {}", principalId);
        //TODO check security constraints

        final User principal = userRepository.findOne(principalId);

        final List<WorkoutPlan> models = workoutPlanRepository.workoutPlans(principal.getId());
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
        final List<WorkoutPlan> workoutPlans = workoutPlanRepository.findByUser(principalId);
        final Optional<WorkoutPlan> activeWorkoutPlan = workoutPlans.stream().filter(WorkoutPlan::getActive).findAny();

        final ZonedDateTime now = ZonedDateTime.now();
        final Optional<Workout> todaysWorkout = activeWorkoutPlan.map(wp -> {
            return wp.getWorkouts().stream().filter(w -> {
                return w.getDate().isAfter(now.withHour(0)) && w.getDate().isBefore(now.withHour(23));
            }).findFirst();
        }).orElse(empty());

        final Long workoutPlanId = activeWorkoutPlan.map(WorkoutPlan::getId).orElse(null);
        final String workoutPlanName = activeWorkoutPlan.map(WorkoutPlan::getName).orElse(null);

        final Long workoutId = todaysWorkout.map(Workout::getId).orElse(null);
        final String workoutName = todaysWorkout.map(Workout::getName).orElse(null);
        return new QuickInfoResponse(workoutPlanId, workoutPlanName, workoutId, workoutName);
    }

    @Transactional(readOnly = true)
    public WorkoutsResponse workouts(WorkoutsRequest dto, Long principalId) {
        log.debug("workouts {} {}", dto, principalId);

        final User principal = userRepository.findOne(principalId);

        final WorkoutPlan workoutPlan = workoutPlanRepository.findOne(dto.getId());

        return new WorkoutsResponse(workoutPlan.getId(), workoutPlan.getName(), workoutPlan.getActive(), workoutPlan.getWorkouts().stream().sorted(new Comparator<Workout>() {
            @Override
            public int compare(Workout o1, Workout o2) {
                return o1.getId() > o2.getId() ? -1 : 1;
            }
        }).map(w -> {
            return new WorkoutsResponseWorkouts(w.getId(), w.getDate(), w.getName(), w.getExercises().stream().map(e -> {
                return new WorkoutsResponseWorkoutsExercises(e.getName(), e.getGoalReps(), e.getGoalWeight(), e.getSet1Reps(), e.getSet1Weight(), e.getSet2Reps(), e.getSet2Weight(), e.getSet3Reps(), e.getSet3Weight());
            }).collect(Collectors.toList()));
        }).collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    public WorkoutsResponseWorkouts workout(WorkoutRequest dto, Long principalId) {
        log.debug("workout {} {}", dto, principalId);
        //TODO check security constraints(id)

        final User principal = userRepository.findOne(principalId);

        final Workout workout = workoutRepository.findOne(dto.getId());
        return return new WorkoutsResponseWorkouts(workout.getId(), workout.getDate(), workout.getName(), workout.getExercises().stream().map(e -> {
            return new WorkoutsResponseWorkoutsExercises(e.getName(), e.getGoalReps(), e.getGoalWeight(), e.getSet1Reps(), e.getSet1Weight(), e.getSet2Reps(), e.getSet2Weight(), e.getSet3Reps(), e.getSet3Weight());
        }).collect(Collectors.toList()));
    }

    public void updateWorkout(UpdateWorkoutRequest dto, Long principalId) {
        log.debug("updateWorkout {} {}", dto, principalId);

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
