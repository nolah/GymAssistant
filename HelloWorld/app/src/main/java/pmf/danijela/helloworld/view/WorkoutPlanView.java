package pmf.danijela.helloworld.view;


import android.content.Context;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pmf.danijela.helloworld.R;
import pmf.danijela.helloworld.activity.WorkoutsActivity_;
import pmf.danijela.helloworld.dto.WorkoutPlansResponse;


@EViewGroup(R.layout.item_view_workout_plans)
public class WorkoutPlanView extends RelativeLayout {

    @ViewById
    TextView workoutPlanName;

    @ViewById
    Button goToWorkouts;



    private WorkoutPlansResponse workoutPlan;

    public WorkoutPlanView(Context context) {
        super(context);
    }

    public WorkoutPlanView bind(WorkoutPlansResponse workoutPlan) {
        this.workoutPlan = workoutPlan;
        workoutPlanName.setText(workoutPlan.getName());
        return this;
    }

    @Click
    void goToWorkouts(){
        final WorkoutsActivity_.IntentBuilder_ intentBuilder_ =  WorkoutsActivity_.intent(getContext());
        intentBuilder_.extra("workoutPlanId", workoutPlan.getId());
        intentBuilder_.startForResult(100);
    }

}

