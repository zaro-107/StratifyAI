package com.stratifyai.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stratifyai.engine.ModelFactory;
import com.stratifyai.engine.PredictionStrategy;

@Service
public class AnalysisOrchestrator {

    private final DataIngestionService ingestionService;
    private final TrendAnalysisService trendService;
    private final ModelFactory modelFactory;

    @Autowired
    public AnalysisOrchestrator(DataIngestionService ingestionService, 
                                TrendAnalysisService trendService, 
                                ModelFactory modelFactory) {
        this.ingestionService = ingestionService;
        this.trendService = trendService;
        this.modelFactory = modelFactory;
    }

    public Map<String, Object> executeFullAnalysis(MultipartFile file) throws Exception {
        // Ingest and clean data (skips text automatically)
        Map<String, List<Double>> cleanedData = ingestionService.parseCsvData(file);
        Map<String, Object> finalResults = new LinkedHashMap<>();

        for (Map.Entry<String, List<Double>> entry : cleanedData.entrySet()) {
            String columnName = entry.getKey();
            List<Double> columnValues = entry.getValue();

            if (columnValues.isEmpty()) continue;

            // Pattern recognition (INCREASING/DECREASING)
            String pattern = trendService.recognizePattern(columnValues);

            // Select strategy based on Column Name
            PredictionStrategy model = modelFactory.getStrategyForColumn(columnName, columnValues);
            double prediction = model.predict(columnValues);

            // Build the detailed response object
            Map<String, Object> columnMetrics = new LinkedHashMap<>();
            columnMetrics.put("pattern", pattern);
            columnMetrics.put("prediction", prediction);
            columnMetrics.put("modelUsed", model.getModelType());
            columnMetrics.put("historicalData", columnValues); // Included for Graphing
            columnMetrics.put("dataPoints", columnValues.size());

            // Handle specialized grade labeling
            if (model.getModelType().equals("STUDENT_CLASSIFIER")) {
                columnMetrics.put("suggestedGrade", interpretGrade(prediction));
            }

            finalResults.put(columnName, columnMetrics);
        }

        return finalResults;
    }

    private String interpretGrade(double score) {
        if (score == 4.0) return "A (Excellent)";
        if (score == 3.0) return "B (Good)";
        if (score == 2.0) return "C (Average)";
        return "F (Action Required)";
    }
}