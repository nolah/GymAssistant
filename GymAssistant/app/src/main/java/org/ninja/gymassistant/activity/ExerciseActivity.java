package org.ninja.gymassistant.activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.ninja.gymassistant.R;
import org.ninja.gymassistant.dto.WorkoutsResponseWorkouts;
import org.ninja.gymassistant.fragment.ExerciseFragment;

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
