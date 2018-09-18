package pmf.danijela.helloworld.http;

import java.util.List;

import pmf.danijela.helloworld.dto.QuickInfoResponse;
import pmf.danijela.helloworld.dto.StartWorkoutPlanRequest;
import pmf.danijela.helloworld.dto.UpdateWorkoutRequest;
import pmf.danijela.helloworld.dto.WorkoutPlansResponse;
import pmf.danijela.helloworld.dto.WorkoutsResponse;
import pmf.danijela.helloworld.dto.WorkoutsResponseWorkouts;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface SecuredApiService {

    @POST("/api/start-workout")
    Call<Void> startWorkout();

    @GET("/api/quick-info")
    Call<QuickInfoResponse> quickInfo();

    @POST("/api/start-workout-plan")
    Call<Void> startWorkoutPlan(@Body StartWorkoutPlanRequest request);

    @GET("/api/workout-plans")
    Call<List<WorkoutPlansResponse>> workoutPlans();

    @GET("/api/workouts")
    Call<WorkoutsResponse> workouts(@Query("id") Long id);

    @GET("/api/workout")
    Call<WorkoutsResponseWorkouts> workout(@Query("id") Long id);

    @PUT("/api/update-workout")
    Call<Void> updateWorkout(@Body UpdateWorkoutRequest request);
}
