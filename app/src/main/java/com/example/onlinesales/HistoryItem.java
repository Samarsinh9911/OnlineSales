package com.example.onlinesales;

import androidx.room.PrimaryKey;

import java.util.List;

public class HistoryItem {
        @PrimaryKey(autoGenerate = true)
        private long id;

        private String expression;
        private String result;

        public HistoryItem(String expression, String result) {
            this.expression = expression;
            this.result = result;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

    public void insertHistoryItem(HistoryItem historyItem) {
    }

    public List<HistoryItem> getAllHistoryItems() {
        return getAllHistoryItems();
    }
}



