package com.bcit.ma_kim;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RelativeAdapter extends RecyclerView.Adapter<RelativeAdapter.ViewHolder> {

    private BloodPressureReading[] data = null;
    private Relative relative;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public RelativeAdapter(Relative relative) {
        this.relative = relative;
    }

    @NonNull
    @Override
    public RelativeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_relative, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull RelativeAdapter.ViewHolder holder, final int position) {
        final CardView cardView = holder.cardView;
        TextView sys_reading = cardView.findViewById(R.id.sys_reading);
        TextView dia_reading = cardView.findViewById(R.id.dia_reading);
        TextView date = cardView.findViewById(R.id.date_entered);
        TextView condition = cardView.findViewById(R.id.cond_reading);
        sys_reading.setText(cardView.getResources().getText(R.string.systolic_readings) + ": " + data[position].getSystolicReading());
        dia_reading.setText(cardView.getResources().getText(R.string.diastolic_reading) + ": " + data[position].getDiastolicReading());
        date.setText(cardView.getResources().getText(R.string.reading_date) + ": " + data[position].getDate());
        condition.setText(cardView.getResources().getText(R.string.condition_reading) + ": " + data[position].getCondition());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cardView.getContext(), EditEntryActivity.class);
                i.putExtra("BloodPressure",data[position]);
                i.putExtra("relative", RelativeAdapter.this.relative.getTitle());
                cardView.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {

        if(data != null) {
            return data.length;
        }else{
            return 0;
        }

    }

    public void setData(BloodPressureReading[] data){
        this.data = data;
    }
}

