package pmf.danijela.gymassistant.adapter;

import android.content.Context;
import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import pmf.danijela.gymassistant.dto.WorkoutPlansResponse;
import pmf.danijela.gymassistant.view.WorkoutPlanView;
import pmf.danijela.helloworld.view.WorkoutPlanView_;

@EBean
public class WorkoutPlansAdapter
        extends RecyclerViewAdapterBase<WorkoutPlansResponse, WorkoutPlanView> {

    @RootContext
    Context context;

    public void initFor(List<WorkoutPlansResponse> items){
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    protected WorkoutPlanView onCreateItemView(ViewGroup parent, int viewType) {
        return WorkoutPlanView_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<WorkoutPlanView> holder,
                                 int position) {
        final WorkoutPlansResponse item = items.get(position);
        holder.getView().bind(item);
    }
}
