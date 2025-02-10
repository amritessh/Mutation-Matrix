package com.mutationmatrix.reporting_service.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportRequestDTO {
    private String patientId;
    private List<String> variantIds;
    private List<String> biomarkerIds;
}
