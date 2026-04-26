package com.stratifyai.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/analyze")
@CrossOrigin(origins = "http://localhost:3000")
public class AnalysisController {

    @PostMapping
    public Map<String, Object> processAnalysis(@RequestParam("file") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();
        List<Double> historicalData = new ArrayList<>();
        List<String> importantFeatures = new ArrayList<>();
        int rowCount = 0;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            
            // TASK: IDENTIFY IMPORTANT FEATURES (HEADERS)
            String headerLine = reader.readLine();
            if (headerLine != null) {
                // Splits CSV headers to identify features like MinTemp, MaxTemp, etc.
                importantFeatures = Arrays.asList(headerLine.split(","));
            }

            // TASK: COUNT ROWS & PROCESS DATA
            String line;
            while ((line = reader.readLine()) != null) {
                rowCount++; 
                String[] values = line.split(",");
                try {
                    // Extracting numerical trend data (e.g., Temperature or Price)
                    historicalData.add(Double.parseDouble(values[1])); 
                } catch (Exception e) { /* Skip non-numeric data */ }
            }

            // TASK: DYNAMIC INDEX (Last real entry in file)
            double latestValue = historicalData.isEmpty() ? 0.0 : historicalData.get(historicalData.size() - 1);

            Map<String, Object> profile = new HashMap<>();
            profile.put("rowCount", rowCount);
            profile.put("columnCount", importantFeatures.size());
            profile.put("mainFeatures", importantFeatures);

            response.put("prediction", latestValue);
            response.put("profile", profile);
            response.put("historicalData", historicalData);

        } catch (Exception e) {
            response.put("error", e.getMessage());
        }
        return response;
    }
}