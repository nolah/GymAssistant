package org.ninja.gymassistant.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.ninja.gymassistant.R;
import org.ninja.gymassistant.activity.BaseActivity;
import org.ninja.gymassistant.activity.MainActivity_;
import org.ninja.gymassistant.adapter.ExerciseAdapter;
import org.ninja.gymassistant.dto.UpdateWorkoutRequest;
import org.ninja.gymassistant.dto.UpdateWorkoutRequestExercises;
import org.ninja.gymassistant.dto.WorkoutsResponseWorkouts;
import org.ninja.gymassistant.http.SecuredApiService;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EFragment(R.layout.fragment_exercise)
public class ExerciseFragment extends Fragment {

    @Bean
    ExerciseAdapter exerciseAdapter;

    @ViewById
    TextView workoutName;

    @ViewById
    TextView workoutDate;

    @ViewById
    RecyclerView recyclerView;

    SecuredApiService securedApiService;

    private WorkoutsResponseWorkouts workout;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        securedApiService = ((BaseActivity) context).getSecuredApiService();
    }

    @Click
    void updateWorkout() {
        final List<UpdateWorkoutRequestExercises> exercises = workout.getExercises().stream().map(exercise ->
                new UpdateWorkoutRequestExercises(exercise.getId(), exercise.getSet1Reps(), exercise.getSet1Weight(), exercise.getSet2Reps(), exercise.getSet2Weight(), exercise.getSet3Reps(), exercise.getSet3Weight())
        ).collect(Collectors.toList());
        final UpdateWorkoutRequest request = new UpdateWorkoutRequest(workout.getId(), exercises);

        final Call<Void> call = securedApiService.updateWorkout(request);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                MainActivity_.intent(getContext()).startForResult(100);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

    public void loadExercise(Long workoutId) {
        final Call<WorkoutsResponseWorkouts> call = securedApiService.workout(workoutId);
        call.enqueue(new Callback<WorkoutsResponseWorkouts>() {
            @Override
            public void onResponse(Call<WorkoutsResponseWorkouts> call, Response<WorkoutsResponseWorkouts> response) {
                loadExercise(response.body());
            }

            @Override
            public void onFailure(Call<WorkoutsResponseWorkouts> call, Throwable t) {
            }
        });
    }

    public void loadExercise(WorkoutsResponseWorkouts workout) {
        this.workout = workout;
        workoutName.setText(workout.getName());
        workoutDate.setText(workout.getDate().toString());

        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(exerciseAdapter);
        exerciseAdapter.initFor(workout.getExercises());
    }

}
