package pmf.danijela.helloworld.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;
import java.util.stream.Collectors;

import pmf.danijela.helloworld.R;
import pmf.danijela.helloworld.adapter.ExerciseAdapter;
import pmf.danijela.helloworld.dto.UpdateWorkoutRequest;
import pmf.danijela.helloworld.dto.UpdateWorkoutRequestExercises;
import pmf.danijela.helloworld.dto.WorkoutsResponseWorkouts;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EActivity(R.layout.activity_exercise)
public class ExerciseActivity extends BaseActivity {

    @Bean
    ExerciseAdapter exerciseAdapter;

    @ViewById
    TextView workoutName;

    @ViewById
    TextView workoutDate;

    @ViewById
    RecyclerView recyclerView;

    @ViewById
    Button updateWorkout;


    private WorkoutsResponseWorkouts workoutsResponseWorkouts;

    @AfterViews
    void afterViews() {
        final Object workout = getIntent().getExtras().get("workout");

        if (workout == null) {
            // if we don't have whole data set then load data from server
            final Long workoutId = (Long) getIntent().getExtras().get("workoutId");

            final Call<WorkoutsResponseWorkouts> call = securedApiService.workout(workoutId);
            call.enqueue(new Callback<WorkoutsResponseWorkouts>() {
                @Override
                public void onResponse(Call<WorkoutsResponseWorkouts> call, Response<WorkoutsResponseWorkouts> response) {
                    ExerciseActivity.this.workoutsResponseWorkouts = response.body();
                    loadData();
                }

                @Override
                public void onFailure(Call<WorkoutsResponseWorkouts> call, Throwable t) {
                }
            });

        } else {
            this.workoutsResponseWorkouts = (WorkoutsResponseWorkouts) workout;
            loadData();
        }
    }

    private void loadData() {
        workoutName.setText(workoutsResponseWorkouts.getName());
        workoutDate.setText(workoutsResponseWorkouts.getDate().toString());

        recyclerView.setLayoutManager(
                new LinearLayoutManager(ExerciseActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(exerciseAdapter);
        exerciseAdapter.initFor(workoutsResponseWorkouts.getExercises());
    }

    @Click
    void updateWorkout() {
        final List<UpdateWorkoutRequestExercises> exercises = workoutsResponseWorkouts.getExercises().stream().map(exercise ->
                new UpdateWorkoutRequestExercises(exercise.getId(), exercise.getSet1Reps(), exercise.getSet1Weight(), exercise.getSet2Reps(), exercise.getSet2Weight(), exercise.getSet3Reps(), exercise.getSet3Weight())
        ).collect(Collectors.toList());
        final UpdateWorkoutRequest request = new UpdateWorkoutRequest(workoutsResponseWorkouts.getId(), exercises);

        final Call<Void> call = securedApiService.updateWorkout(request);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                MainActivity_.intent(ExerciseActivity.this).startForResult(100);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

}
