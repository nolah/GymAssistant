package pmf.danijela.helloworld.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import pmf.danijela.helloworld.R;
import pmf.danijela.helloworld.adapter.WorkoutsAdapter;
import pmf.danijela.helloworld.dto.WorkoutsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EActivity(R.layout.activity_workouts)
public class WorkoutsActivity extends BaseActivity {


    @Bean
    WorkoutsAdapter workoutsAdapter;

    @ViewById
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Long workoutPlanId = (Long) getIntent().getExtras().get("workoutPlanId");

        final Call<WorkoutsResponse> call = securedApiService.workouts(workoutPlanId);
        call.enqueue(new Callback<WorkoutsResponse>() {
            @Override
            public void onResponse(Call<WorkoutsResponse> call, Response<WorkoutsResponse> response) {
                recyclerView.setLayoutManager(
                        new LinearLayoutManager(WorkoutsActivity.this, LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(workoutsAdapter);
                workoutsAdapter.initFor(response.body().getWorkouts());
            }

            @Override
            public void onFailure(Call<WorkoutsResponse> call, Throwable t) {
                Throwable a = t;
            }
        });
    }
}
