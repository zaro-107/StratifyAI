package com.stratifyai.engine.models;

import java.util.List;

import org.springframework.stereotype.Component;

import com.stratifyai.engine.PredictionStrategy;

@Component
public class LinearRegressionStrategy implements PredictionStrategy {
    @Override
    public double predict(List<Double> data) {
        int n = data.size();
        if (n < 2) {
            if (data.isEmpty()) return 0;
            Double first = data.get(0);
            return first == null ? 0 : first;
        }
        
        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0;
        for (int i = 0; i < n; i++) {
            sumX += i;
            sumY += data.get(i);
            sumXY += i * data.get(i);
            sumX2 += i * i;
        }
        
        double slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        double intercept = (sumY - slope * sumX) / n;
        return (slope * n) + intercept;
    }

    @Override
    public String getModelType() { return "LINEAR_REGRESSION"; }
}