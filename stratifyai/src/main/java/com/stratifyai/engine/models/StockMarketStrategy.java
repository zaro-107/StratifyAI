package com.stratifyai.engine.models;

import java.util.List;

import org.springframework.stereotype.Component;

import com.stratifyai.engine.PredictionStrategy;

@Component
public class StockMarketStrategy implements PredictionStrategy {

    @Override
    public String getModelType() {
        return "STOCK_MARKET_WMA";
    }

    @Override
    public double predict(List<Double> data) {
        if (data.size() < 3) {
            Double last = data.isEmpty() ? null : data.get(data.size() - 1);
            return (last == null) ? 0.0 : last;
        }

        // Weighted Moving Average (WMA) logic
        // Gives more importance to recent prices
        double weightedSum = 0;
        double weightTotal = 0;

        for (int i = 0; i < data.size(); i++) {
            double weight = i + 1;
            weightedSum += data.get(i) * weight;
            weightTotal += weight;
        }

        return weightedSum / weightTotal;
    }

    // New Analytical Features for your Advanced Dashboard
    public double calculateVolatility(List<Double> data) {
        if (data.isEmpty()) return 0.0;
        double mean = data.stream().mapToDouble(d -> d).average().orElse(0.0);
        double variance = data.stream().mapToDouble(d -> Math.pow(d - mean, 2)).average().orElse(0.0);
        return Math.sqrt(variance); 
    }

    public String getLiquidityStatus(List<Double> data) {
        if (data.isEmpty()) return "N/A";
        double avgPrice = data.stream().mapToDouble(d -> d).average().orElse(0.0);
        // Logic: Higher priced stocks often represent higher market cap/liquidity pools
        return (avgPrice > 100) ? "HIGH" : "MODERATE";
    }
}