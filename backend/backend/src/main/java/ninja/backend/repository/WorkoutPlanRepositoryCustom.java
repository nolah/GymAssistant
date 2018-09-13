package ninja.backend.repository;

import java.util.List;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;
import ninja.backend.repository.tuple.*;


public interface WorkoutPlanRepositoryCustom {

    List<WorkoutPlan> findByUser(Long userId);

    List<WorkoutPlan> findByName(String name);

    List<WorkoutPlan> findByActive(Boolean active);

    List<WorkoutPlan> workoutPlans(Long principalUser);

}
