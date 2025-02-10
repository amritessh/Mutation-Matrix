package com.mutationmatrix.reporting_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutationmatrix.reporting_service.model.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

}
