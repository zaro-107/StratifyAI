package com.stratifyai.engine.models;

import java.util.List;

import org.springframework.stereotype.Component;

import com.stratifyai.engine.PredictionStrategy;

@Component
public class StudentStrategy implements PredictionStrategy {
    @Override
    public String getModelType() { return "STUDENT_CLASSIFIER"; }

    @Override
    public double predict(List<Double> data) {
        return data.stream().mapToDouble(d -> d).average().orElse(0.0);
    }

    public String classifyStudent(double avgScore) {
        if (avgScore >= 75) return "DISTINCTION";
        if (avgScore >= 40) return "PASS";
        return "AT_RISK";
    }
}