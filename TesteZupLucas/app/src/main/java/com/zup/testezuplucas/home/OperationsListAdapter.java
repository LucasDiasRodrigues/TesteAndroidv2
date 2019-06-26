package com.zup.testezuplucas.home;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.zup.testezuplucas.R;
import com.zup.testezuplucas.model.Operation;
import com.zup.testezuplucas.util.ValueFormatter;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class OperationsListAdapter extends RecyclerView.Adapter<OperationsListAdapter.OperationsViewHolder> {
    private ArrayList<Operation> operations;

    public OperationsListAdapter(ArrayList<Operation> operations) {
        this.operations = operations;
    }

    @NonNull
    @Override
    public OperationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_operations, parent, false);
        return new OperationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OperationsViewHolder holder, int position) {
        holder.operationType.setText(operations.get(position).getTitle());
        holder.operationDate.setText(ValueFormatter.formatDate(operations.get(position).getDate()));
        holder.operationName.setText(operations.get(position).getDesc());
        holder.operationValue.setText(ValueFormatter.formatCurrency(operations.get(position).getValue()));
    }

    @Override
    public int getItemCount() {
        return operations.size();
    }

    public static class OperationsViewHolder extends RecyclerView.ViewHolder {
        TextView operationType;
        TextView operationName;
        TextView operationDate;
        TextView operationValue;

        public OperationsViewHolder(View v) {
            super(v);
            operationType = v.findViewById(R.id.operationType);
            operationName = v.findViewById(R.id.operationName);
            operationDate = v.findViewById(R.id.operationDate);
            operationValue = v.findViewById(R.id.operationValue);
        }
    }

}
