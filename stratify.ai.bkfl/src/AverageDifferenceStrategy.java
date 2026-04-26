package com.stratifyai.engine.models;

import java.util.List;

interface PredictionStrategy {
    double predict(List<Double> data);
    String getModelType();
}

public class AverageDifferenceStrategy implements PredictionStrategy {
    
    @Override
    public double predict(List<Double> data) { // Ensure this is a lowercase 'double'
        if (data.size() < 2) {
            if (!data.isEmpty()) {
            } else {
                return 0;
            }
            Double first = data.get(0);
            return first == null ? 0 : first;
        }
        double sumDiff = 0;
        for (int i = 1; i < data.size(); i++) {
            sumDiff += (data.get(i) - data.get(i - 1));
        }
        return data.get(data.size() - 1) + (sumDiff / (data.size() - 1));
    }

    @Override
    public String getModelType() { 
        return "AVERAGE_DIFFERENCE"; 
    }
}