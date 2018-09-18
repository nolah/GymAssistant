package org.ninja.gymassistant.activity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;

import org.ninja.gymassistant.dto.WorkoutPlansResponse;
import org.ninja.gymassistant.fragment.WorkoutPlansFragment;
import org.ninja.gymassistant.R;
import org.ninja.gymassistant.activity.WorkoutsActivity_;

@EActivity(R.layout.activity_workout_plans)
public class WorkoutPlansActivity extends BaseActivity implements WorkoutPlansFragment.WorkoutPlansHandler {

    @FragmentById
    WorkoutPlansFragment workoutPlansFragment;

    @Override
    public void initialWorkoutPlanSelected(WorkoutPlansResponse workoutPlan) {
        // do nothing
    }

    @Override
    public void workoutPlanSelected(WorkoutPlansResponse workoutPlan) {
        final WorkoutsActivity_.IntentBuilder_ intentBuilder_ = WorkoutsActivity_.intent(this);
        intentBuilder_.extra("workoutPlanId", workoutPlan.getId());
        intentBuilder_.extra("workoutPlanName", workoutPlan.getName());
        intentBuilder_.startForResult(100);
    }

}

