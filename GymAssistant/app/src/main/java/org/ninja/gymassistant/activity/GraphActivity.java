package org.ninja.gymassistant.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.ninja.gymassistant.R;
import org.ninja.gymassistant.dto.WorkoutsResponseWorkouts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@EActivity(R.layout.activity_graph)
public class GraphActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {


    private List<WorkoutsResponseWorkouts> workouts;

    @ViewById
    GraphView graph;

    @ViewById
    Spinner exerciseSpinner;

    private Map<String, List<Pair<Integer, Pair<Double, Double>>>> graphData = new HashMap<>();

    private String[] exerciseArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        workouts = Arrays.asList((WorkoutsResponseWorkouts[]) getIntent().getExtras().get("workouts"));

        for (int i = 0; i < this.workouts.size(); i++) {
            final int week = i / 4;
            final int ii = week + 1;

            workouts.get(i).getExercises().forEach(exercise -> {
                graphData.putIfAbsent(exercise.getName(), new ArrayList<>());

                final double expectedWeight = exercise.getGoalWeight().doubleValue();
                final double actualResult = (exercise.getSet1Weight().add(exercise.getSet2Weight()).add(exercise.getSet3Weight()).doubleValue() / 3)
                        * (exercise.getSet1Reps() + exercise.getSet2Reps() + exercise.getSet3Reps())
                        / (3 * exercise.getGoalReps());
                graphData.get(exercise.getName()).add(new Pair<>(ii, new Pair<>(expectedWeight, actualResult)));
            });
        }
        exerciseArray = graphData.keySet().toArray(new String[1]);
    }

    @AfterViews
    void afterViews() {
        final ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, exerciseArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exerciseSpinner.setAdapter(adapter);
        exerciseSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        final List<Pair<Integer, Pair<Double, Double>>> firstSet = graphData.get(exerciseArray[pos]);
        final List<DataPoint> expectedDataPoints = firstSet.stream().map(pair -> new DataPoint(pair.first, pair.second.first)).collect(Collectors.toList());
        final LineGraphSeries<DataPoint> expectedSeries = new LineGraphSeries<>(expectedDataPoints.toArray(new DataPoint[0]));

        graph.getSeries().clear();
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(10);
        expectedSeries.setColor(Color.RED);
        expectedSeries.setDrawDataPoints(true);
        graph.addSeries(expectedSeries);
        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return "w " + super.formatLabel(value, isValueX);
                } else {
                    // show currency for y values
                    return super.formatLabel(value, isValueX) + " kg";
                }
            }
        });
        final List<DataPoint> actualDataPoints = firstSet.stream().map(pair -> new DataPoint(pair.first, pair.second.second)).collect(Collectors.toList());
        final LineGraphSeries<DataPoint> actualSeries = new LineGraphSeries<>(actualDataPoints.toArray(new DataPoint[0]));
        actualSeries.setDrawDataPoints(true);
        graph.addSeries(actualSeries);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback

    }


}
