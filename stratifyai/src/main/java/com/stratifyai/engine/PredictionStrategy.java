package com.stratifyai.engine;
import java.util.List;

public interface PredictionStrategy {
    // Allows orchestrator to run the ML logic
    double predict(List<Double> data);

    // Allows orchestrator to identify which model is running
    String getModelType(); 
}