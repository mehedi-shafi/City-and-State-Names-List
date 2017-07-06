package com.example.shafi.databaseimport.data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.shafi.databaseimport.CityList;
import com.example.shafi.databaseimport.R;
import com.example.shafi.databaseimport.model.Country;
import com.example.shafi.databaseimport.model.State;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by shafi on 7/5/2017.
 */

public class StateListAdapter extends ArrayAdapter{

    private ArrayList<State> stateList;
    private Activity activity;
    private int layoutResource;

    public StateListAdapter (Activity act, int resource, ArrayList<State> data){

        super(act, resource, data);
        layoutResource = resource;
        activity = act;
        stateList = data;
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return stateList.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return stateList.get(position);
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        StateViewHolder holder = null;

        if (holder == null || row.getTag() == null){

            LayoutInflater inflater = LayoutInflater.from(activity);
            row = inflater.inflate(layoutResource, null);

            holder = new StateViewHolder();

            holder.stateid = (TextView) row.findViewById(R.id.stateListStateId);
            holder.stateName = (TextView) row.findViewById(R.id.stateListStateName);

            row.setTag(holder);

        }
        else{
            holder = (StateViewHolder) row.getTag();
        }

        holder.state = (State) getItem(position);

        holder.stateid.setText(String.valueOf(holder.state.getId()));
        holder.stateName.setText(holder.state.getName());

        final StateViewHolder finalHolder = holder;
        row.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, CityList.class);

                Bundle mBundle = new Bundle();
                mBundle.putSerializable("data", finalHolder.state);
                i.putExtras(mBundle);

                activity.startActivity(i);
            }
        });

        return row;
    }

    public class StateViewHolder {

        State state;
        TextView stateName;
        TextView stateid;
        int stateIdInt;

    }
}
