package com.stratifyai.engine;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TrendBenchmarkService {
    public Map<String, Object> compareWithTrends(double userPrediction, String domain) {
        Map<String, Object> comparison = new HashMap<>();
        double globalTrend = domain.equals("weather") ? 22.5 : 45.0; // Mock global benchmarks
        
        double variance = ((userPrediction - globalTrend) / globalTrend) * 100;
        
        comparison.put("globalBenchmark", globalTrend);
        comparison.put("variance", variance);
        comparison.put("alignment", variance > 0 ? "OUTPERFORMING" : "UNDERPERFORMING");
        return comparison;
    }
}