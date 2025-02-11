package com.mutationmatrix.reporting_service.service;

import com.mutationmatrix.reporting_service.dto.ReportRequestDTO;
import com.mutationmatrix.reporting_service.dto.ReportResponseDTO;

public interface ReportService {
    ReportResponseDTO generateReport(ReportRequestDTO request);

    ReportResponseDTO getReport(Long id);
}
