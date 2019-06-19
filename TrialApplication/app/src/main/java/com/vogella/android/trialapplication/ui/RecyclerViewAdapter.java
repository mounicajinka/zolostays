package com.vogella.android.trialapplication.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.vogella.android.trialapplication.App;
import com.vogella.android.trialapplication.R;
import com.vogella.android.trialapplication.model.AdapterData;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<String> listOfItems = new ArrayList<>();
    List<AdapterData> adapterDataList = new ArrayList<>();

    public RecyclerViewAdapter(List<String> listOfItems) {
        this.listOfItems = listOfItems;
        for (int i=0; i<listOfItems.size(); i++) {
            adapterDataList.add(new AdapterData(listOfItems.get(i), "A", 0.0));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup, false);
        return new RecyclerViewAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.itemName.setText(adapterDataList.get(viewHolder.getAdapterPosition()).getItemname());

        final ArrayList<String> vesselList = new ArrayList<>();
        vesselList.add("A");
        vesselList.add("B");
        vesselList.add("C");
        ArrayAdapter<String> vesselAdapter = new ArrayAdapter<>(App.getInstance(), android.R.layout.simple_spinner_dropdown_item, vesselList);
        viewHolder.vesselSpinner.setAdapter(vesselAdapter);

        viewHolder.vesselSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != -1) {
                    AdapterData adapterData = adapterDataList.get(viewHolder.getAdapterPosition());
                    adapterData.setVessel_id(vesselList.get(position));
                    adapterDataList.remove(viewHolder.getAdapterPosition());
                    adapterDataList.add(viewHolder.getAdapterPosition(), adapterData);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        viewHolder.etWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                AdapterData adapterData = adapterDataList.get(viewHolder.getAdapterPosition());
                adapterData.setWeight(Double.parseDouble(viewHolder.etWeight.getText().toString()));
                adapterDataList.remove(viewHolder.getAdapterPosition());
                adapterDataList.add(viewHolder.getAdapterPosition(), adapterData);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        EditText etWeight;
        Spinner vesselSpinner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.itemName);
            etWeight = itemView.findViewById(R.id.etWeight);
            vesselSpinner = itemView.findViewById(R.id.vesselSpinner);
        }
    }

    public List<AdapterData> getAdapterData(){
        return adapterDataList;
    }
}
