package com.rh.grh.service;

import com.rh.grh.dto.LeaveDTO;

import java.time.LocalDate;
import java.util.List;

public interface LeaveService {

    LeaveDTO createLeave(LeaveDTO dto);

    LeaveDTO updateLeave(Long id, LeaveDTO dto);

    void deleteLeave(Long id);

    LeaveDTO getLeaveById(Long id);

    List<LeaveDTO> getAllLeaves();

    List<LeaveDTO> getLeavesByEmployee(Long employeeId);

    List<LeaveDTO> getLeavesByStatus(String status);

    List<LeaveDTO> getLeavesByEmployeeAndDateRange(Long employeeId, LocalDate start, LocalDate end);
}
