package pmf.danijela.helloworld.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.math.BigDecimal;

import pmf.danijela.helloworld.R;
import pmf.danijela.helloworld.dto.StartWorkoutPlanRequest;
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

        final StartWorkoutPlanRequest request = new StartWorkoutPlanRequest(maxSquat, maxBench, maxDeadlift);

        final SharedPreferences prefs = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        final String token = prefs.getString("token", null);
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
