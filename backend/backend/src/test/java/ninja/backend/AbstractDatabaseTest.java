package ninja.backend;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringRunner;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.inject.Inject;
import java.io.IOException;
import com.fasterxml.jackson.annotation.JsonInclude;
import ninja.backend.config.CustomProperties;
import ninja.backend.repository.*;
import ninja.backend.api.dto.*;
import java.util.List;
import java.util.LinkedList;
import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import ninja.backend.model.*;
import eu.execom.fabut.Fabut;
import eu.execom.fabut.IFabutRepositoryTest;


@ActiveProfiles(resolver = TestProfileResolver.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = BackendApplication.class)
@Transactional
public abstract class AbstractDatabaseTest implements IFabutRepositoryTest {

    @Inject
    private CustomProperties customProperties;

    @Inject
    protected UserRepository userRepository;

    @Inject
    protected WorkoutPlanRepository workoutPlanRepository;

    @Inject
    protected WorkoutRepository workoutRepository;

    @Inject
    protected ExerciseRepository exerciseRepository;

    @Before
    public void setup() {
        Fabut.beforeTest(this);
    }

    @After
    public void after() {
        Fabut.afterTest();
    }

    @Override
    public void fabutBeforeTest() {
        //TODO I am not sure what this method is for, fabut works without implementing it
    }

    @Override
    public void fabutAfterTest() {
        //TODO I am not sure what this method is for, fabut works without implementing it
    }

    @Override
    public List<Class<?>> getComplexTypes() {
        List<Class<?>> complexTypes = new LinkedList<>();
        complexTypes.add(SignInResponse.class);
        complexTypes.add(RefreshTokenRequest.class);
        complexTypes.add(SignUpRequest.class);
        complexTypes.add(SignInRequest.class);
        complexTypes.add(ChangePasswordRequest.class);
        complexTypes.add(StartWorkoutPlanRequest.class);
        complexTypes.add(WorkoutPlansResponse.class);
        complexTypes.add(QuickInfoResponse.class);
        complexTypes.add(WorkoutsRequest.class);
        complexTypes.add(WorkoutsResponse.class);
        complexTypes.add(WorkoutsResponseWorkoutsExercises.class);
        complexTypes.add(WorkoutsResponseWorkouts.class);
        complexTypes.add(WorkoutRequest.class);
        complexTypes.add(UpdateWorkoutRequest.class);
        complexTypes.add(UpdateWorkoutRequestExercises.class);
        return complexTypes;
    }

    @Override
    public List<Class<?>> getIgnoredTypes() {
        return new LinkedList<>();
    }

    @Override
    public void customAssertEquals(Object expected, Object actual) {
        Assert.assertEquals(expected, actual);
    }

    @Override
    public List<?> findAll(Class<?> clazz) {
        if (clazz == User.class) {
            return userRepository.findAll();
        } else if (clazz == WorkoutPlan.class) {
            return workoutPlanRepository.findAll();
        } else if (clazz == Workout.class) {
            return workoutRepository.findAll();
        } else if (clazz == Exercise.class) {
            return exerciseRepository.findAll();
        }

        throw new IllegalStateException("No findAll for class: " + clazz.getName());
    }

    @Override
    public Object findById(final Class<?> entityClass, final Object id) {
        if (entityClass == User.class) {
            return userRepository.findOne((Long) id);
        } else if (entityClass == WorkoutPlan.class) {
            return workoutPlanRepository.findOne((Long) id);
        } else if (entityClass == Workout.class) {
            return workoutRepository.findOne((Long) id);
        } else if (entityClass == Exercise.class) {
            return exerciseRepository.findOne((Long) id);
        }

        throw new IllegalStateException("No findById for class: " + entityClass.getName());
    }

    @Override
    public List<Class<?>> getEntityTypes() {
        List<Class<?>> entityTypes = new LinkedList<>();
        entityTypes.add(User.class);
        entityTypes.add(WorkoutPlan.class);
        entityTypes.add(Workout.class);
        entityTypes.add(Exercise.class);
        return entityTypes;
    }

}
