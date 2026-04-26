package com.stratifyai.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DataIngestionService {

    public Map<String, List<Double>> parseCsvData(MultipartFile file) throws Exception {
        Map<String, List<Double>> columnData = new LinkedHashMap<>();

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             @SuppressWarnings("deprecation")
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            // Initialize lists for each header
            for (String header : csvParser.getHeaderNames()) {
                columnData.put(header, new ArrayList<>());
            }

            // Populate data, safely ignoring non-numeric values
            for (CSVRecord csvRecord : csvParser) {
                for (String header : csvParser.getHeaderNames()) {
                    try {
                        double value = Double.parseDouble(csvRecord.get(header));
                        columnData.get(header).add(value);
                    } catch (NumberFormatException e) {
                        // Skip empty or non-numeric cells automatically
                    }
                }
            }
        }
        return columnData;
    }
}