package org.ninja.gymassistant.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.ninja.gymassistant.R;
import org.ninja.gymassistant.activity.BaseActivity;
import org.ninja.gymassistant.adapter.WorkoutPlansAdapter;
import org.ninja.gymassistant.dto.WorkoutPlansResponse;
import org.ninja.gymassistant.http.SecuredApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EFragment(R.layout.fragment_workout_plans)
public class WorkoutPlansFragment extends Fragment {

    @ViewById
    RecyclerView recyclerView;

    @Bean
    WorkoutPlansAdapter workoutPlansAdapter;

    private List<WorkoutPlansResponse> workoutPlans;

    SecuredApiService securedApiService;
    WorkoutPlansHandler workoutPlansHandler;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            securedApiService = ((BaseActivity) context).getSecuredApiService();
            workoutPlansHandler = ((WorkoutPlansHandler) context);
        } catch (ClassCastException e) {
            throw new ClassCastException(context
                    + " must inherit BaseActivity!");
        }

        loadWorkoutPlans();

    }

    public void loadWorkoutPlans(){
        final Call<List<WorkoutPlansResponse>> call = securedApiService.workoutPlans();
        call.enqueue(new Callback<List<WorkoutPlansResponse>>() {
            @Override
            public void onResponse(Call<List<WorkoutPlansResponse>> call, Response<List<WorkoutPlansResponse>> response) {

                WorkoutPlansFragment.this.workoutPlans = response.body();
                recyclerView.setLayoutManager(
                        new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(workoutPlansAdapter);
                workoutPlansAdapter.initFor(workoutPlans);

                if(!workoutPlans.isEmpty()){
                    workoutPlansHandler.initialWorkoutPlanSelected(workoutPlans.get(0));
                }
            }

            @Override
            public void onFailure(Call<List<WorkoutPlansResponse>> call, Throwable t) {
            }
        });
    }


    public interface  WorkoutPlansHandler{
        void initialWorkoutPlanSelected(WorkoutPlansResponse workoutPlansResponse);
        void workoutPlanSelected(WorkoutPlansResponse workoutPlansResponse);
    }

}
