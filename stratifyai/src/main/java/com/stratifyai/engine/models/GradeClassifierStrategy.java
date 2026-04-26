package com.stratifyai.engine.models;

import java.util.List;

import org.springframework.stereotype.Component;

import com.stratifyai.engine.PredictionStrategy;

@Component
public class GradeClassifierStrategy implements PredictionStrategy {
    @Override
    public double predict(List<Double> data) {
        // Simple classifier: Average of internal scores
        double avg = data.stream().mapToDouble(d -> d).average().orElse(0.0);
        
        // Return a numerical code for grades: 4.0=A, 3.0=B, 2.0=C, 1.0=F
        if (avg >= 90) return 4.0;
        if (avg >= 80) return 3.0;
        if (avg >= 70) return 2.0;
        return 1.0;
    }

    @Override
    public String getModelType() { return "STUDENT_CLASSIFIER"; }
}