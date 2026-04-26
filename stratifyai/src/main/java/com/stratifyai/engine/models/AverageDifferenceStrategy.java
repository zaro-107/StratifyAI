package com.stratifyai.engine.models;

import com.stratifyai.engine.PredictionStrategy;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AverageDifferenceStrategy implements PredictionStrategy {
    
    @Override
    public double predict(List<Double> data) { // Ensure this is a lowercase 'double'
        if (data.size() < 2) {
            Double firstValue = data.isEmpty() ? null : data.get(0);
            return firstValue == null ? 0 : firstValue;
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