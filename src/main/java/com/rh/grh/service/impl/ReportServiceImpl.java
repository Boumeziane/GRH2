package com.rh.grh.service.impl;

import com.rh.grh.dto.ReportDTO;
import com.rh.grh.entity.Employee;
import com.rh.grh.repository.AbsenceRepository;
import com.rh.grh.repository.EmployeeRepository;
import com.rh.grh.repository.EvaluationRepository;
import com.rh.grh.repository.LeaveRepository;
import com.rh.grh.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final EmployeeRepository employeeRepository;
    private final AbsenceRepository absenceRepository;
    private final LeaveRepository leaveRepository;
    private final EvaluationRepository evaluationRepository;

    @Override
    public List<ReportDTO> generateEmployeeReports() {
        return employeeRepository.findAll()
                .stream()
                .map(employee -> {
                    int totalAbsences = absenceRepository.findByEmployee(employee).size();
                    int totalLeaves = leaveRepository.findByEmployee(employee).size();
                    double averageEvaluation = evaluationRepository.findByEmployee(employee)
                            .stream()
                            .mapToInt(e -> e.getNote() != null ? e.getNote() : 0)
                            .average()
                            .orElse(0.0);

                    ReportDTO dto = new ReportDTO();
                    dto.setEmployeeName(employee.getNom() + " " + employee.getPrenom());
                    dto.setEmployeeEmail(employee.getEmail());
                    dto.setPoste(employee.getPoste());
                    dto.setTotalAbsences(totalAbsences);
                    dto.setTotalLeaves(totalLeaves);
                    dto.setAverageEvaluation(averageEvaluation);

                    return dto;
                })
                .collect(Collectors.toList());
    }
}
