package pmf.danijela.helloworld.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import pmf.danijela.helloworld.http.PublicApiService;
import pmf.danijela.helloworld.R;
import pmf.danijela.helloworld.adapter.WorkoutPlansAdapter;
import pmf.danijela.helloworld.dto.WorkoutPlansResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EActivity(R.layout.activity_workout_plans)
public class WorkoutPlansActivity extends BaseActivity {


    @Bean
    WorkoutPlansAdapter workoutPlansAdapter;

    @ViewById
    RecyclerView recyclerView;

    private PublicApiService publicApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Call<List<WorkoutPlansResponse>> call = securedApiService.workoutPlans();
        call.enqueue(new Callback<List<WorkoutPlansResponse>>() {
            @Override
            public void onResponse(Call<List<WorkoutPlansResponse>> call, Response<List<WorkoutPlansResponse>> response) {
                recyclerView.setLayoutManager(
                        new LinearLayoutManager(WorkoutPlansActivity.this, LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(workoutPlansAdapter);
                workoutPlansAdapter.initFor(response.body());
            }

            @Override
            public void onFailure(Call<List<WorkoutPlansResponse>> call, Throwable t) {
            }
        });
    }
}
