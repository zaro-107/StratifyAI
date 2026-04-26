package com.stratifyai.engine.models;

import java.util.List;

import org.springframework.stereotype.Component;

import com.stratifyai.engine.PredictionStrategy;

@Component
public class WeatherStrategy implements PredictionStrategy {
    @Override
    public String getModelType() { return "WEATHER_FORECASTER"; }

    @Override
    public double predict(List<Double> data) {
        if (data.size() < 2) return data.isEmpty() ? 0.0 : data.get(0);
        // Calculates atmospheric pressure/temp shift using delta changes
        double lastValue = data.get(data.size() - 1);
        double firstValue = data.get(0);
        double avgChange = (lastValue - firstValue) / data.size();
        return lastValue + (avgChange * 1.5); // Predicts next atmospheric state
    }
    
    public String getPrecipitationRisk(List<Double> data) {
        double lastValue = data.get(data.size() - 1);
        return (lastValue > 30) ? "STORM_WARNING" : "CLEAR_SKIES";
    }
}