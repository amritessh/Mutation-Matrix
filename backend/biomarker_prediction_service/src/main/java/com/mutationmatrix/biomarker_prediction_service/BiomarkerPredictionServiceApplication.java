package com.mutationmatrix.biomarker_prediction_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class,
		BatchAutoConfiguration.class // Add this
})
public class BiomarkerPredictionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiomarkerPredictionServiceApplication.class, args);
	}

}
