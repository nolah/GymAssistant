package org.ninja.gymassistant.activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.ninja.gymassistant.R;
import org.ninja.gymassistant.dto.WorkoutPlansResponse;
import org.ninja.gymassistant.dto.WorkoutsResponseWorkouts;
import org.ninja.gymassistant.fragment.ExerciseFragment;
import org.ninja.gymassistant.fragment.WorkoutFragment;
import org.ninja.gymassistant.fragment.WorkoutPlansFragment;

import java.util.Arrays;

@EActivity(R.layout.activity_exercise_dashboard)
public class ExerciseDashboardActivity extends BaseActivity implements WorkoutFragment.WorkoutHandler, WorkoutPlansFragment.WorkoutPlansHandler {

    @FragmentById
    WorkoutFragment workoutFragment;

    @FragmentById
    ExerciseFragment exerciseFragment;

    @Override
    public void initialWorkoutSelected(WorkoutsResponseWorkouts workout){

    }

    @Override
    public void workoutSelected(WorkoutsResponseWorkouts workout){
        exerciseFragment.loadExercise(workout);
    }

    @AfterViews
    void afterViews(){
        final WorkoutsResponseWorkouts workout = (WorkoutsResponseWorkouts) getIntent().getExtras().get("workout");
        final WorkoutsResponseWorkouts [] workouts = (WorkoutsResponseWorkouts []) getIntent().getExtras().get("workouts");
        workoutFragment.loadWorkouts(Arrays.asList(workouts));
        exerciseFragment.loadExercise(workout);
    }

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
