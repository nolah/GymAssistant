package org.ninja.gymassistant.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.EditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.ninja.gymassistant.R;
import org.ninja.gymassistant.dto.StartWorkoutPlanRequest;

import java.math.BigDecimal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EActivity(R.layout.activity_start_workout_plan)
public class StartWorkoutPlanActivity extends BaseActivity {

    @ViewById
    EditText maxSquat;

    @ViewById
    EditText maxBench;

    @ViewById
    EditText maxDeadlift;

    @ViewById
    Button startWorkoutPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Click
    void startWorkoutPlan() {
        final String maxSquatText = maxSquat.getText().toString();
        final String maxBenchText = maxBench.getText().toString();
        final String maxDeadliftText = maxDeadlift.getText().toString();

        final BigDecimal maxSquat = BigDecimal.valueOf(Double.parseDouble(maxSquatText));
        final BigDecimal maxBench = BigDecimal.valueOf(Double.parseDouble(maxBenchText));
        final BigDecimal maxDeadlift = BigDecimal.valueOf(Double.parseDouble(maxDeadliftText));

        if (getIntent().getExtras() != null && getIntent().getExtras().get("activeWorkoutPlanId") != null) {
            final String activeWorkoutPlanName = getIntent().getExtras().getString("activeWorkoutPlanName");
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.confirm))
                    .setMessage(getString(R.string.start_workout_plan_confirmation_message, activeWorkoutPlanName))
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            performCall(maxSquat, maxBench, maxDeadlift);
                        }
                    })
                    .setNegativeButton(android.R.string.no, null).show();
        } else {
            performCall(maxSquat, maxBench, maxDeadlift);
        }


    }

    private void performCall(BigDecimal maxSquat, BigDecimal maxBench, BigDecimal maxDeadlift) {
        final StartWorkoutPlanRequest request = new StartWorkoutPlanRequest(maxSquat, maxBench, maxDeadlift);

        final Call<Void> call = securedApiService.startWorkoutPlan(request);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                MainActivity_.intent(StartWorkoutPlanActivity.this).startForResult(100);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }
}
