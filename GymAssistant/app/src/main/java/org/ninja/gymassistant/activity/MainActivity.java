package org.ninja.gymassistant.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.ninja.gymassistant.R;
import org.ninja.gymassistant.dto.QuickInfoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewById
    Button startWorkoutPlan;

    @ViewById
    Button goToTodaysWorkout;

    @ViewById
    Button goToWorkoutPlans;

    private QuickInfoResponse quickInfoResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void afterViews() {
        final Call<QuickInfoResponse> call = securedApiService.quickInfo();
        call.enqueue(new Callback<QuickInfoResponse>() {
            @Override
            public void onResponse(Call<QuickInfoResponse> call, Response<QuickInfoResponse> response) {
                MainActivity.this.quickInfoResponse = response.body();

                if (response.body().getTodaysWorkoutId() != null) {
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
    void startWorkoutPlan() {
        final StartWorkoutPlanActivity_.IntentBuilder_ intentBuilder_ = StartWorkoutPlanActivity_.intent(this);
        intentBuilder_.extra("activeWorkoutPlanId", quickInfoResponse.getActiveWorkoutPlanId());
        intentBuilder_.extra("activeWorkoutPlanName", quickInfoResponse.getActiveWorkoutPlanName());
        intentBuilder_.startForResult(100);
    }

    @Click
    void goToTodaysWorkout() {
        final ExerciseActivity_.IntentBuilder_ intentBuilder_ = ExerciseActivity_.intent(this);
        intentBuilder_.extra("workoutId", quickInfoResponse.getTodaysWorkoutId());
        intentBuilder_.startForResult(100);
    }

    @Click
    void goToWorkoutPlans() {
        if (isScreenLarge()) {
            WorkoutPlansDashboardActivity_.intent(this).startForResult(100);
        } else {
            WorkoutPlansActivity_.intent(this).startForResult(100);
        }
    }

    public boolean isScreenLarge() {
        final int screenSize = getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK;
        return screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE
                || screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }
}
