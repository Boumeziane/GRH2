package com.rh.grh.service;

import com.rh.grh.dto.ReportDTO;
import java.util.List;

public interface ReportService {
    List<ReportDTO> generateEmployeeReports();
}
