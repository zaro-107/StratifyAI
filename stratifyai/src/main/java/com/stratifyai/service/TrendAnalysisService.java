package com.stratifyai.service;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrendAnalysisService {

    public String recognizePattern(List<Double> data) {
        if (data == null || data.isEmpty()) return "Unknown";

        double first = data.get(0);
        double last = data.get(data.size() - 1);
        
        // Add a small threshold to avoid calling minor fluctuations "stable"
        double threshold = first * 0.01; // 1% threshold

        if (last > first + threshold) {
            return "Increasing Trend ↑";
        } else if (last < first - threshold) {
            return "Decreasing Trend ↓";
        } else {
            return "Stable Trend →";
        }
    }
}