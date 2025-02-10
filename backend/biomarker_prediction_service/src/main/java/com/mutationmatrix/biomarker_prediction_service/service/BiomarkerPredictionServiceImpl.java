package com.mutationmatrix.biomarker_prediction_service.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.InputField;
import org.springframework.beans.factory.annotation.Autowired;

import com.mutationmatrix.biomarker_prediction_service.dto.PredictionRequestDTO;
import com.mutationmatrix.biomarker_prediction_service.dto.PredictionResponseDTO;
import com.mutationmatrix.biomarker_prediction_service.model.BiomarkerPrediction;
import com.mutationmatrix.biomarker_prediction_service.repository.BiomarkerPredictionRepository;

public class BiomarkerPredictionServiceImpl implements BiomarkerPredictionService {

    @Autowired
    private Evaluator biomarkerModel;

    @Autowired
    private BiomarkerPredictionRepository predictionRepository;

    @Override
    public PredictionResponseDTO predictBiomarker(PredictionRequestDTO request) {
        // TODO Auto-generated method stub
        try {
            // Prepare input
            Map<String, Object> arguments = new LinkedHashMap<>();
            List<InputField> inputFields = biomarkerModel.getInputFields();
            for (InputField inputField : inputFields) {
                String inputName = inputField.getName().getValue();
                // Assume the input is the sequence data for simplicity
                // In a real scenario, you'd need to extract relevant features from the sequence
                arguments.put(inputName, request.getSequenceData());
            }

            // Make prediction
            Map<String, ?> results = biomarkerModel.evaluate(arguments);

            // Extract prediction results
            // This assumes the model output is named "probability_1" for the positive class
            double probability = (Double) results.get("probability_1");
            boolean isBiomarkerPresent = probability > 0.5;

            // Save prediction to database
            BiomarkerPrediction prediction = new BiomarkerPrediction();
            prediction.setSequenceData(request.getSequenceData());
            prediction.setBiomarkerPresent(isBiomarkerPresent);
            prediction.setProbability(probability);
            predictionRepository.save(prediction);

            // Create and return response
            PredictionResponseDTO response = new PredictionResponseDTO();
            response.setBiomarkerPresent(isBiomarkerPresent);
            response.setConfidence(probability);
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Error predicting biomarker", e);
        }
    }
}
