package org.ninja.gymassistant.adapter;

import android.content.Context;
import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.ninja.gymassistant.dto.WorkoutPlansResponse;
import org.ninja.gymassistant.view.WorkoutPlanView;
import org.ninja.gymassistant.view.WorkoutPlanView_;

import java.util.List;

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
