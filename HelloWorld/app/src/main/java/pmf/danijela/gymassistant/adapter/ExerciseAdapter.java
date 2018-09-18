package pmf.danijela.gymassistant.adapter;

import android.content.Context;
import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import pmf.danijela.gymassistant.dto.WorkoutsResponseWorkoutsExercises;
import pmf.danijela.gymassistant.view.ExerciseView;
import pmf.danijela.helloworld.view.ExerciseView_;

@EBean
public class ExerciseAdapter
        extends RecyclerViewAdapterBase<WorkoutsResponseWorkoutsExercises, ExerciseView> {

    @RootContext
    Context context;

    public void initFor(List<WorkoutsResponseWorkoutsExercises> items){
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    protected ExerciseView onCreateItemView(ViewGroup parent, int viewType) {
        return ExerciseView_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<ExerciseView> holder,
                                 int position) {
        final WorkoutsResponseWorkoutsExercises item = items.get(position);
        holder.getView().bind(item);
    }
}
