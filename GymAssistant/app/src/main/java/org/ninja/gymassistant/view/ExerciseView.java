package org.ninja.gymassistant.view;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.ninja.gymassistant.R;
import org.ninja.gymassistant.dto.WorkoutsResponseWorkoutsExercises;

import java.math.BigDecimal;
import java.util.function.Consumer;


@EViewGroup(R.layout.item_view_exercise)
public class ExerciseView extends RelativeLayout {

    @ViewById
    TextView exerciseName;

    @ViewById
    TextView goalRepsAndWeight;

    @ViewById
    EditText set1Reps;

    @ViewById
    EditText set1Weight;

    @ViewById
    EditText set2Reps;

    @ViewById
    EditText set2Weight;

    @ViewById
    EditText set3Reps;

    @ViewById
    EditText set3Weight;

    public ExerciseView(Context context) {
        super(context);
    }

    public ExerciseView bind(final WorkoutsResponseWorkoutsExercises exercise) {
        exerciseName.setText(exercise.getName());
        goalRepsAndWeight.setText(getContext().getResources().getString(R.string.goal_reps_and_weight, exercise.getGoalReps().toString(), exercise.getGoalWeight().toString()));

        set1Reps.setText(exercise.getSet1Reps().toString());
        set1Reps.addTextChangedListener(new IntegerTextWatcher((i) -> {
            exercise.setSet1Reps(i);
        }));

        set1Weight.setText(exercise.getSet2Weight().toString());
        set1Weight.addTextChangedListener(new BigDecimalTextWatcher((i) ->{
            exercise.setSet1Weight(i);
        }));

        set2Reps.setText(exercise.getSet2Reps().toString());
        set2Reps.addTextChangedListener(new IntegerTextWatcher((i) -> {
            exercise.setSet2Reps(i);
        }));

        set2Weight.setText(exercise.getSet2Weight().toString());
        set2Weight.addTextChangedListener(new BigDecimalTextWatcher((i) ->{
            exercise.setSet2Weight(i);
        }));

        set3Reps.setText(exercise.getSet1Reps().toString());
        set3Reps.addTextChangedListener(new IntegerTextWatcher((i) -> {
            exercise.setSet1Reps(i);
        }));

        set3Weight.setText(exercise.getSet3Weight().toString());
        set3Weight.addTextChangedListener(new BigDecimalTextWatcher((i) ->{
            exercise.setSet3Weight(i);
        }));

        return this;
    }

}

class IntegerTextWatcher implements TextWatcher {

    private Consumer<Integer> function;

    public IntegerTextWatcher(Consumer<Integer> function) {
        this.function = function;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        try {
            final int value = Integer.parseInt(s.toString());
            function.accept(value);
        } catch (Exception e) {
            function.accept(0);
        }
    }
}

class BigDecimalTextWatcher implements TextWatcher {

    private Consumer<BigDecimal> function;

    public BigDecimalTextWatcher(Consumer<BigDecimal> function) {
        this.function = function;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        try {
            final double value = Double.parseDouble(s.toString());
            function.accept(BigDecimal.valueOf(value));
        }catch (Exception e){
            function.accept(BigDecimal.ZERO);
        }
    }
}

