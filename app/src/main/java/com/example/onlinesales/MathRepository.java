package com.example.onlinesales;

import java.util.List;

public class MathRepository {
    private HistoryItem historyDao;

    public MathRepository() {
    }

    public void insertHistoryItem(String expression, String result) {
        HistoryItem historyItem = new HistoryItem(expression, result);
        historyDao.insertHistoryItem(historyItem);
    }

    public List<HistoryItem> getAllHistoryItems() {
        return historyDao.getAllHistoryItems();
    }
}
