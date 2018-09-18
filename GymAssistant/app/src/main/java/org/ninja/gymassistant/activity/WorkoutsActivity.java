package org.ninja.gymassistant.activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;

import org.ninja.gymassistant.dto.WorkoutsResponseWorkouts;
import org.ninja.gymassistant.fragment.WorkoutFragment;
import org.ninja.gymassistant.R;
import org.ninja.gymassistant.activity.ExerciseActivity_;

@EActivity(R.layout.activity_workouts)
public class WorkoutsActivity extends BaseActivity implements WorkoutFragment.WorkoutHandler {

    @FragmentById
    WorkoutFragment workoutFragment;

    @AfterViews
    void afterViews() {
        final Long workoutPlanId = getIntent().getExtras().getLong("workoutPlanId");
        final String workoutPlanName = getIntent().getExtras().getString("workoutPlanName");
        workoutFragment.loadByWorkoutPlanId(workoutPlanId);
    }

    @Override
    public void initialWorkoutSelected(WorkoutsResponseWorkouts workout) {

    }

    @Override
    public void workoutSelected(WorkoutsResponseWorkouts workout) {
        final ExerciseActivity_.IntentBuilder_ intentBuilder_ = ExerciseActivity_.intent(this);
        intentBuilder_.extra("workout", workout);
        intentBuilder_.extra("workouts", workoutFragment.getWorkouts().toArray(new WorkoutsResponseWorkouts[0]));
        intentBuilder_.startForResult(100);
    }
}
