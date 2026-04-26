package com.stratifyai.engine.models;

import com.stratifyai.engine.PredictionStrategy;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AverageDifferenceStrategy implements PredictionStrategy {

    @Override
    public double predict(List<Double> data) {
        if (data.size() < 2) return data.isEmpty() ? 0 : data.get(0);

        double sumDiff = 0;
        for (int i = 1; i < data.size(); i++) {
            sumDiff += (data.get(i) - data.get(i - 1));
        }

        double avgDiff = sumDiff / (data.size() - 1);
        return data.get(data.size() - 1) + avgDiff;
    }

    @Override
    public String getModelType() {
        return "AVERAGE_DIFFERENCE";
    }
}