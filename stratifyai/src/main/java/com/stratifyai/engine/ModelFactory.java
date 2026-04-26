package com.stratifyai.engine;

import org.springframework.stereotype.Component;
import java.util.*;
@Component
public class ModelFactory {

    public PredictionStrategy getStrategyForColumn(String columnName, List<Double> data) {
        
        // 1. Check for Volatility (High Variance = Stock/Crypto style)
        double variance = calculateVariance(data);
        if (variance > 100) {
            return new StockMarketStrategy(); // High volatility logic
        }

        // 2. Check for Seasonality (Repeating patterns = Weather/Cycles)
        if (isSeasonal(data)) {
            return new WeatherStrategy(); // Cyclic logic
        }

        // 3. Fallback: Linear Regression (General growth/decline)
        // This handles GNP, population, or simple progressive trends
        return new LinearRegressionStrategy();
    }
    
    private double calculateVariance(List<Double> data) {
        double mean = data.stream().mapToDouble(d -> d).average().orElse(0.0);
        return data.stream().mapToDouble(d -> Math.pow(d - mean, 2)).average().orElse(0.0);
    }
}
