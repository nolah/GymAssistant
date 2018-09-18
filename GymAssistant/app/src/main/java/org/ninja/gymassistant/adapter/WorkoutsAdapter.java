package org.ninja.gymassistant.adapter;

import android.content.Context;
import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import org.ninja.gymassistant.dto.WorkoutsResponseWorkouts;
import org.ninja.gymassistant.view.WorkoutView;
import org.ninja.gymassistant.view.WorkoutView_;

@EBean
public class WorkoutsAdapter
        extends RecyclerViewAdapterBase<WorkoutsResponseWorkouts, WorkoutView> {

    @RootContext
    Context context;

    public void initFor(List<WorkoutsResponseWorkouts> items){
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    protected WorkoutView onCreateItemView(ViewGroup parent, int viewType) {
        return WorkoutView_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<WorkoutView> holder,
                                 int position) {
        final WorkoutsResponseWorkouts item = items.get(position);
        holder.getView().bind(item);
    }
}
