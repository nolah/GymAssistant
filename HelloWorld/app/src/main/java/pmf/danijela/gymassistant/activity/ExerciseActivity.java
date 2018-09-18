package pmf.danijela.gymassistant.activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;

import pmf.danijela.gymassistant.dto.WorkoutsResponseWorkouts;
import pmf.danijela.gymassistant.fragment.ExerciseFragment;
import pmf.danijela.helloworld.R;

@EActivity(R.layout.activity_exercise)
public class ExerciseActivity extends BaseActivity {

    @FragmentById
    ExerciseFragment exerciseFragment;

    @AfterViews
    void afterViews() {
        final WorkoutsResponseWorkouts workout = (WorkoutsResponseWorkouts) getIntent().getExtras().get("workout");
        if (workout != null) {
            exerciseFragment.loadExercise(workout);
        } else {
            exerciseFragment.loadExercise(getIntent().getExtras().getLong("workoutId", 0));
        }
    }

}
