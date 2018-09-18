package pmf.danijela.gymassistant.view;


import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pmf.danijela.gymassistant.dto.WorkoutsResponseWorkouts;
import pmf.danijela.gymassistant.fragment.WorkoutFragment;
import pmf.danijela.helloworld.R;


@EViewGroup(R.layout.item_view_workout)
public class WorkoutView extends RelativeLayout {

    @ViewById
    TextView workoutName;

    private WorkoutsResponseWorkouts workout;

    private WorkoutFragment.WorkoutHandler workoutHandler;

    public WorkoutView(Context context) {
        super(context);
        this.workoutHandler = (WorkoutFragment.WorkoutHandler) context;
    }

    public WorkoutView bind(WorkoutsResponseWorkouts workout) {
        this.workout = workout;
        workoutName.setText(workout.getName());
        return this;
    }

    @Click
    void goToExercises() {
        workoutHandler.workoutSelected(this.workout);
    }

}

