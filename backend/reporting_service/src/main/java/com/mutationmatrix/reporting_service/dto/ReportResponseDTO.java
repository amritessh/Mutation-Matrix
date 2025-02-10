package com.mutationmatrix.reporting_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportResponseDTO {
    private Long reportId;
    private String reportUrl;
    private String reportFormat;
}
