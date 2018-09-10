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
    private WorkoutRepository workoutRepository;

    public void startWorkout(Long principalId) {
        log.debug("startWorkout {}", principalId);
        //TODO check security constraints

        final User principal = userRepository.findOne(principalId);

        //TODO process event
        throw new UnsupportedOperationException();
    }

    @Transactional(readOnly = true)
    public List<WorkoutsResponse> workouts(WorkoutsRequest dto, Long principalId) {
        log.debug("workouts {} {}", dto, principalId);
        //TODO check security constraints(userId)

        final User principal = userRepository.findOne(principalId);

        final List<WorkoutWorkoutsTuple> tuples = workoutRepository.workouts(dto.getUserId());
        return tuples.stream().map(tuple -> {
            final Long id = tuple.getWorkout().getId();
            final Long workoutPlanId = tuple.getWorkout().getWorkoutPlan().getId();
            final ZonedDateTime date = tuple.getWorkout().getDate();
            final Integer set1Reps = tuple.getWorkout().getSet1Reps();
            final BigDecimal set1Weight = tuple.getWorkout().getSet1Weight();
            final Integer set2Reps = tuple.getWorkout().getSet2Reps();
            final BigDecimal set2Weight = tuple.getWorkout().getSet2Weight();
            final Integer set3Reps = tuple.getWorkout().getSet3Reps();
            final BigDecimal set3Weight = tuple.getWorkout().getSet3Weight();
            final Long planId = tuple.getWorkoutPlan().getId();
            final Long planUserId = tuple.getWorkoutPlan().getUser().getId();
            final Boolean planActive = tuple.getWorkoutPlan().getActive();
            return new WorkoutsResponse(id, workoutPlanId, date, set1Reps, set1Weight, set2Reps, set2Weight, set3Reps, set3Weight, planId, planUserId, planActive);
        }).collect(Collectors.toList());
    }

    public void updateWorkout(UpdateWorkoutRequest dto, Long principalId) {
        log.debug("updateWorkout {} {}", dto, principalId);
        //TODO check security constraints

        final User principal = userRepository.findOne(principalId);

        final Workout model = new Workout();
        model.setSet1Reps(dto.getSet1Reps());
        model.setSet1Weight(dto.getSet1Weight());
        model.setSet2Reps(dto.getSet2Reps());
        model.setSet2Weight(dto.getSet2Weight());
        model.setSet3Reps(dto.getSet3Reps());
        model.setSet3Weight(dto.getSet3Weight());
        workoutRepository.save(model);
        //TODO process event

    }

}
