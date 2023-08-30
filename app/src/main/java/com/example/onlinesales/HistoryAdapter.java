package com.example.onlinesales;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<HistoryItem> historyItems;

    public HistoryAdapter(List<HistoryItem> historyItems) {
        this.historyItems = historyItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoryItem historyItem = historyItems.get(position);
        holder.bind(historyItem);
    }

    @Override
    public int getItemCount() {
        return historyItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView expressionTextView;
        private TextView resultTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            expressionTextView = itemView.findViewById(R.id.expressionTextView);
            resultTextView = itemView.findViewById(R.id.resultTextView);
        }

        public void bind(HistoryItem historyItem) {
            expressionTextView.setText(historyItem.getExpression());
            resultTextView.setText(historyItem.getResult());
        }
    }
}

