package pmf.danijela.helloworld.view;


import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pmf.danijela.helloworld.R;
import pmf.danijela.helloworld.activity.ExerciseActivity;
import pmf.danijela.helloworld.activity.ExerciseActivity_;
import pmf.danijela.helloworld.activity.WorkoutsActivity_;
import pmf.danijela.helloworld.dto.WorkoutsResponseWorkouts;


/**
 * Created by Alex on 5/20/17.
 */

@EViewGroup(R.layout.item_view_workout)
public class WorkoutView extends RelativeLayout {

    @ViewById
    TextView workoutName;

    private WorkoutsResponseWorkouts workout;

    public WorkoutView(Context context) {
        super(context);
    }

    public WorkoutView bind(WorkoutsResponseWorkouts workout) {
        this.workout = workout;
        workoutName.setText(workout.getName());
        return this;
    }

    @Click
    void goToExercises(){
        final ExerciseActivity_.IntentBuilder_ intentBuilder_ =  ExerciseActivity_.intent(getContext());
        intentBuilder_.extra("workout", workout);
        intentBuilder_.startForResult(100);    }

}

