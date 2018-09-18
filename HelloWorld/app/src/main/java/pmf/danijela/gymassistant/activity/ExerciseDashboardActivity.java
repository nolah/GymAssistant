package pmf.danijela.gymassistant.activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;

import java.util.Arrays;

import pmf.danijela.gymassistant.dto.WorkoutPlansResponse;
import pmf.danijela.gymassistant.dto.WorkoutsResponseWorkouts;
import pmf.danijela.gymassistant.fragment.ExerciseFragment;
import pmf.danijela.gymassistant.fragment.WorkoutFragment;
import pmf.danijela.gymassistant.fragment.WorkoutPlansFragment;
import pmf.danijela.helloworld.R;
import pmf.danijela.helloworld.activity.WorkoutsActivity_;

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
