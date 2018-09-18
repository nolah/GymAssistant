package pmf.danijela.gymassistant.view;


import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pmf.danijela.gymassistant.dto.WorkoutPlansResponse;
import pmf.danijela.gymassistant.fragment.WorkoutPlansFragment;
import pmf.danijela.helloworld.R;


@EViewGroup(R.layout.item_view_workout_plan)
public class WorkoutPlanView extends RelativeLayout {

    @ViewById
    TextView workoutPlanName;

    private WorkoutPlansResponse workoutPlansResponse;

    private WorkoutPlansFragment.WorkoutPlansHandler workoutPlansHandler;

    public WorkoutPlanView(Context context) {
        super(context);
        this.workoutPlansHandler = (WorkoutPlansFragment.WorkoutPlansHandler) context;
    }

    public WorkoutPlanView bind(WorkoutPlansResponse workoutPlansResponse) {
        this.workoutPlansResponse = workoutPlansResponse;
        workoutPlanName.setText(workoutPlansResponse.getName());
        return this;
    }

    @Click
    void goToWorkouts() {
        workoutPlansHandler.workoutPlanSelected(workoutPlansResponse);
    }

}

