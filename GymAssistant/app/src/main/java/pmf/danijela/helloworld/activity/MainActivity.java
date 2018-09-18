package pmf.danijela.helloworld.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import pmf.danijela.helloworld.R;
import pmf.danijela.helloworld.dto.QuickInfoResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewById
    Button startWorkoutPlan;

    @ViewById
    Button goToActiveWorkoutPlan;

    @ViewById
    Button goToTodaysWorkout;

    @ViewById
    Button goToWorkoutPlans;

    private QuickInfoResponse quickInfoResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Call<QuickInfoResponse> call = securedApiService.quickInfo();
        call.enqueue(new Callback<QuickInfoResponse>() {
            @Override
            public void onResponse(Call<QuickInfoResponse> call, Response<QuickInfoResponse> response) {
                MainActivity.this.quickInfoResponse = response.body();

                if(response.body().getActiveWorkoutPlanId() != null){
                    goToActiveWorkoutPlan.setVisibility(View.VISIBLE);
                    goToActiveWorkoutPlan.setText(response.body().getActiveWorkoutPlanName());
                }
                if(response.body().getTodaysWorkoutId() != null){
                    goToTodaysWorkout.setVisibility(View.VISIBLE);
                    goToTodaysWorkout.setText(response.body().getTodaysWorkoutName());
                }
            }

            @Override
            public void onFailure(Call<QuickInfoResponse> call, Throwable t) {
            }
        });
    }

    @Click
    void startWorkoutPlan(){
        StartWorkoutPlanActivity_.intent(this).startForResult(100);
    }

    @Click
    void goToActiveWorkoutPlan(){
        final WorkoutsActivity_.IntentBuilder_ intentBuilder_ =  WorkoutsActivity_.intent(this);
        intentBuilder_.extra("workoutPlanId", quickInfoResponse.getActiveWorkoutPlanId());
        intentBuilder_.startForResult(100);
    }

    @Click
    void goToTodaysWorkout(){
        final ExerciseActivity_.IntentBuilder_ intentBuilder_ =  ExerciseActivity_.intent(this);
        intentBuilder_.extra("workoutId", quickInfoResponse.getTodaysWorkoutId());
        intentBuilder_.startForResult(100);
    }

    @Click
    void goToWorkoutPlans(){
        WorkoutPlansActivity_.intent(this).startForResult(100);
    }
}
