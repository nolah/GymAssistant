package pmf.danijela.gymassistant.activity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;

import pmf.danijela.gymassistant.dto.WorkoutPlansResponse;
import pmf.danijela.gymassistant.fragment.WorkoutPlansFragment;
import pmf.danijela.helloworld.R;
import pmf.danijela.helloworld.activity.WorkoutsActivity_;

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

