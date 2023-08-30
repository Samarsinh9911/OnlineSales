package com.example.onlinesales;

import androidx.lifecycle.ViewModel;

import com.example.onlinesales.HistoryItem;
import com.example.onlinesales.MainActivity;
import com.example.onlinesales.MathRepository;

import java.util.List;

public class MainViewModel extends ViewModel {
    private MathRepository repository;

    public MainViewModel() {
        // Initialize your repository here with a proper HistoryDao instance
        repository = new MathRepository(); // Pass any required dependencies
    }

    public void insertHistoryItem(String expression, String result) {
        repository.insertHistoryItem(expression, result);
    }

    public List<HistoryItem> getAllHistoryItems() {
        return repository.getAllHistoryItems();
    }
}
