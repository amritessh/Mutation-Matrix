package com.mutationmatrix.reporting_service.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.mutationmatrix.reporting_service.dto.ReportRequestDTO;
import com.mutationmatrix.reporting_service.dto.ReportResponseDTO;
import com.mutationmatrix.reporting_service.model.Report;
import com.mutationmatrix.reporting_service.repository.ReportRepository;

public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public ReportResponseDTO generateReport(ReportRequestDTO request) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'generateReport'");

        Context context = new Context();
        context.setVariable("patientId", request.getPatientId());

        context.setVariable("variants", request.getVariantIds());

        context.setVariable("biomarkers", request.getBiomarkerIds());

        String reportContent = templateEngine.process("pdf-report", context);

        String reportUrl = saveReportToStorage(reportContent);

        Report report = new Report();

        report.setPatientId(request.getPatientId());
        report.setReportUrl(reportUrl);
        report.setReportFormat("PDF");

        report.setGeneratedAt(LocalDateTime.now());
        report = reportRepository.save(report);

        ReportResponseDTO response = new ReportResponseDTO();
        response.setReportId(report.getId());
        response.setReportUrl(reportUrl);
        response.setReportFormat("PDF");

        return response;
    }

    @Override
    public ReportResponseDTO getReport(Long id) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getReport'");

        Report report = reportRepository.findById(id).orElseThrow(() -> new RuntimeException("Report not found"));

        ReportResponseDTO response = new ReportResponseDTO();
        response.setReportId(report.getId());

        response.setReportUrl(report.getReportUrl());
        response.setReportFormat(report.getReportFormat());
        return response;
    }

    private String saveReportToStorage(String reportContent) {

        return "https://storage.mutationmatrix.com/reports/" + UUID.randomUUID() + ".pdf";
    }

}
