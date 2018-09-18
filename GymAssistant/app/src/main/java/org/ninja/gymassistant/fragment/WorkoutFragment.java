package org.ninja.gymassistant.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import org.ninja.gymassistant.dto.WorkoutsResponse;
import org.ninja.gymassistant.dto.WorkoutsResponseWorkouts;
import org.ninja.gymassistant.R;
import org.ninja.gymassistant.activity.BaseActivity;
import org.ninja.gymassistant.activity.GraphActivity_;
import org.ninja.gymassistant.adapter.WorkoutsAdapter;
import org.ninja.gymassistant.http.SecuredApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EFragment(R.layout.fragment_workouts)
public class WorkoutFragment extends Fragment {

    @ViewById
    RecyclerView recyclerView;

    @Bean
    WorkoutsAdapter workoutsAdapter;

    private List<WorkoutsResponseWorkouts> workouts;

    SecuredApiService securedApiService;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            securedApiService = ((BaseActivity) context).getSecuredApiService();
        } catch (ClassCastException e) {
            throw new ClassCastException(context
                    + " must inherit BaseActivity!");
        }
    }

    @Click
    void goToGraph() {
        final GraphActivity_.IntentBuilder_ intentBuilder_ = GraphActivity_.intent(this);
        intentBuilder_.extra("workouts", workouts.toArray(new WorkoutsResponseWorkouts[0]));
        intentBuilder_.startForResult(100);
    }

    public void loadByWorkoutPlanId(Long workoutPlanId) {
        final Call<WorkoutsResponse> call = securedApiService.workouts(workoutPlanId);
        call.enqueue(new Callback<WorkoutsResponse>() {
            @Override
            public void onResponse(Call<WorkoutsResponse> call, Response<WorkoutsResponse> response) {
                loadWorkouts(response.body().getWorkouts());
            }

            @Override
            public void onFailure(Call<WorkoutsResponse> call, Throwable t) {
            }
        });
    }

    public void loadWorkouts(List<WorkoutsResponseWorkouts> workouts) {
        this.workouts = workouts;
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(workoutsAdapter);
        workoutsAdapter.initFor(workouts);
    }

    public List<WorkoutsResponseWorkouts> getWorkouts() {
        return workouts;
    }

    public interface WorkoutHandler {
        void initialWorkoutSelected(WorkoutsResponseWorkouts workout);

        void workoutSelected(WorkoutsResponseWorkouts workout);
    }

}
