package com.rh.grh.service.impl;

import com.rh.grh.dto.LeaveDTO;
import com.rh.grh.entity.Employee;
import com.rh.grh.entity.Leave;
import com.rh.grh.mapper.LeaveMapper;
import com.rh.grh.repository.EmployeeRepository;
import com.rh.grh.repository.LeaveRepository;
import com.rh.grh.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveMapper leaveMapper;

    @Override
    public LeaveDTO createLeave(LeaveDTO dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Leave leave = leaveMapper.toEntity(dto);
        leave.setEmployee(employee);

        Leave saved = leaveRepository.save(leave);
        return leaveMapper.toDTO(saved);
    }

    @Override
    public LeaveDTO updateLeave(Long id, LeaveDTO dto) {
        Leave leave = leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setStatus(dto.getStatus());

        if (dto.getEmployeeId() != null) {
            Employee employee = employeeRepository.findById(dto.getEmployeeId())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            leave.setEmployee(employee);
        }

        Leave updated = leaveRepository.save(leave);
        return leaveMapper.toDTO(updated);
    }

    @Override
    public void deleteLeave(Long id) {
        leaveRepository.deleteById(id);
    }

    @Override
    public LeaveDTO getLeaveById(Long id) {
        Leave leave = leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));
        return leaveMapper.toDTO(leave);
    }

    @Override
    public List<LeaveDTO> getAllLeaves() {
        return leaveRepository.findAll()
                .stream()
                .map(leaveMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LeaveDTO> getLeavesByEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return leaveRepository.findByEmployee(employee)
                .stream()
                .map(leaveMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LeaveDTO> getLeavesByStatus(String status) {
        return leaveRepository.findByStatus(status)
                .stream()
                .map(leaveMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LeaveDTO> getLeavesByEmployeeAndDateRange(Long employeeId, LocalDate start, LocalDate end) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return leaveRepository.findByEmployeeAndStartDateBetween(employee, start, end)
                .stream()
                .map(leaveMapper::toDTO)
                .collect(Collectors.toList());
    }
}
