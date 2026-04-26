package com.stratifyai.engine;

import org.springframework.stereotype.Component;

@Component
public class SystemComplianceEvaluator {
    public String evaluateCompliance(double score) {
        if (score >= 85) return "OPTIMIZED PERFORMANCE (GRADE A)";
        if (score >= 65) return "STABLE OPERATION (GRADE B)";
        return "ADJUSTMENT REQUIRED (GRADE C)";
    }
}