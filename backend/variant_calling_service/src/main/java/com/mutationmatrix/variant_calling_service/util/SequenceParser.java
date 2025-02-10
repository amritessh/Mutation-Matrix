package com.mutationmatrix.variant_calling_service.util;

public class SequenceParser {
    public static String parseSequence(String rawSequence) {
        // Implement sequence parsing logic
        return rawSequence.toUpperCase().replaceAll("[^ATCG]", "");
    }
}