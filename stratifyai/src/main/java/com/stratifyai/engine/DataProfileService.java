package com.stratifyai.engine;

import java.io.*;
import java.util.*;

public class DataProfileService {

    public Map<String, Object> getDatasetProfile(String filePath) {
        Map<String, Object> profile = new HashMap<>();
        int rowCount = 0;
        String[] headers = null;

        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
            String line;
            if ((line = br.readLine()) != null) {
                headers = line.split(","); // Identifying Columns
            }
            while (br.readLine() != null) {
                rowCount++; // Counting Rows
            }
        } catch (IOException e) {
            System.err.println("Profiling Error: " + e.getMessage());
        }

        profile.put("rowCount", rowCount);
        profile.put("columnCount", headers != null ? headers.length : 0);
        profile.put("mainFeatures", headers != null ? Arrays.asList(headers) : new ArrayList<>());
        return profile;
    }
}