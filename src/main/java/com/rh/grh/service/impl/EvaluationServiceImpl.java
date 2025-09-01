package com.rh.grh.service.impl;

import com.rh.grh.dto.EvaluationDTO;
import com.rh.grh.entity.Employee;
import com.rh.grh.entity.Evaluation;
import com.rh.grh.mapper.EvaluationMapper;
import com.rh.grh.repository.EmployeeRepository;
import com.rh.grh.repository.EvaluationRepository;
import com.rh.grh.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {


    private final EvaluationRepository evaluationRepository;
    private final EmployeeRepository employeeRepository;
    private final EvaluationMapper evaluationMapper;

    @Override
    public EvaluationDTO createEvaluation(EvaluationDTO dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Evaluation evaluation = evaluationMapper.toEntity(dto);
        evaluation.setEmployee(employee);

        Evaluation saved = evaluationRepository.save(evaluation);
        return evaluationMapper.toDTO(saved);
    }

    @Override
    public EvaluationDTO updateEvaluation(Long id, EvaluationDTO dto) {
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));

        evaluation.setDate(dto.getDate());
        evaluation.setCommentaire(dto.getCommentaire());
        evaluation.setNote(dto.getNote());

        if (dto.getEmployeeId() != null) {
            Employee employee = employeeRepository.findById(dto.getEmployeeId())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            evaluation.setEmployee(employee);
        }

        Evaluation updated = evaluationRepository.save(evaluation);
        return evaluationMapper.toDTO(updated);
    }

    @Override
    public void deleteEvaluation(Long id) {
        evaluationRepository.deleteById(id);
    }

    @Override
    public EvaluationDTO getEvaluationById(Long id) {
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));
        return evaluationMapper.toDTO(evaluation);
    }

    @Override
    public List<EvaluationDTO> getEvaluationsByEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return evaluationRepository.findByEmployee(employee)
                .stream()
                .map(evaluationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EvaluationDTO> getEvaluationsByDateRange(LocalDate start, LocalDate end) {
        return evaluationRepository.findByDateBetween(start, end)
                .stream()
                .map(evaluationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EvaluationDTO> getEvaluationsByEmployeeAndDateRange(Long employeeId, LocalDate start, LocalDate end) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return evaluationRepository.findByEmployeeAndDateBetween(employee, start, end)
                .stream()
                .map(evaluationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EvaluationDTO> getAllEvaluations() {
        return evaluationRepository.findAll()
                .stream()
                .map(evaluationMapper::toDTO)
                .collect(Collectors.toList());
    }
}
