package org.ninja.gymassistant.activity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;

import org.ninja.gymassistant.dto.WorkoutPlansResponse;
import org.ninja.gymassistant.dto.WorkoutsResponseWorkouts;
import org.ninja.gymassistant.fragment.WorkoutFragment;
import org.ninja.gymassistant.fragment.WorkoutPlansFragment;
import org.ninja.gymassistant.R;
import org.ninja.gymassistant.activity.ExerciseDashboardActivity_;

@EActivity(R.layout.activity_workout_plans_dashboard)
public class WorkoutPlansDashboardActivity extends BaseActivity implements WorkoutPlansFragment.WorkoutPlansHandler, WorkoutFragment.WorkoutHandler {

    @FragmentById
    WorkoutPlansFragment workoutPlansFragment;

    @FragmentById
    WorkoutFragment workoutFragment;

    @Override
    public void initialWorkoutPlanSelected(WorkoutPlansResponse workoutPlan) {
        workoutFragment.loadByWorkoutPlanId(workoutPlan.getId());
    }

    @Override
    public void workoutPlanSelected(WorkoutPlansResponse workoutPlan) {
        workoutFragment.loadByWorkoutPlanId(workoutPlan.getId());
    }

    @Override
    public void initialWorkoutSelected(WorkoutsResponseWorkouts workout) {

    }

    @Override
    public void workoutSelected(WorkoutsResponseWorkouts workout) {
        final ExerciseDashboardActivity_.IntentBuilder_ intentBuilder_ = ExerciseDashboardActivity_.intent(this);
        intentBuilder_.extra("workout", workout);
        intentBuilder_.extra("workouts", workoutFragment.getWorkouts().toArray(new WorkoutsResponseWorkouts[0]));
        intentBuilder_.startForResult(100);
    }
}

