package com.rh.grh.service.impl;

import com.rh.grh.dto.AbsenceDTO;
import com.rh.grh.entity.Absence;
import com.rh.grh.entity.Employee;
import com.rh.grh.mapper.AbsenceMapper;
import com.rh.grh.repository.AbsenceRepository;
import com.rh.grh.repository.EmployeeRepository;
import com.rh.grh.service.AbsenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AbsenceServiceImpl implements AbsenceService {

    private final AbsenceRepository absenceRepository;
    private final EmployeeRepository employeeRepository;
    private final AbsenceMapper absenceMapper;

    @Override
    public AbsenceDTO createAbsence(AbsenceDTO dto) {
        Absence absence = absenceMapper.toEntity(dto);

        if (dto.getEmployeeId() != null) {
            Employee employee = employeeRepository.findById(dto.getEmployeeId())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            absence.setEmployee(employee);
        }

        Absence saved = absenceRepository.save(absence);
        return absenceMapper.toDTO(saved);
    }

    @Override
    public AbsenceDTO updateAbsence(Long id, AbsenceDTO dto) {
        Absence abs = absenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absence not found"));

        abs.setDate(dto.getDate());
        abs.setMotif(dto.getMotif());
        abs.setJustifiee(dto.isJustifiee());

        if (dto.getEmployeeId() != null) {
            Employee employee = employeeRepository.findById(dto.getEmployeeId())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            abs.setEmployee(employee);
        }

        Absence updated = absenceRepository.save(abs);
        return absenceMapper.toDTO(updated);
    }

    @Override
    public void deleteAbsence(Long id) {
        absenceRepository.deleteById(id);
    }

    @Override
    public AbsenceDTO getAbsenceById(Long id) {
        Absence absence = absenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absence not found"));
        return absenceMapper.toDTO(absence);
    }

    @Override
    public List<AbsenceDTO> getAllAbsences() {
        return absenceRepository.findAll()
                .stream()
                .map(absenceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AbsenceDTO> getAbsencesByEmployeeId(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return absenceRepository.findByEmployee(employee)
                .stream()
                .map(absenceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AbsenceDTO> getAbsencesByDateRange(LocalDate startDate, LocalDate endDate) {
        return absenceRepository.findAll()
                .stream()
                .filter(a -> !a.getDate().isBefore(startDate) && !a.getDate().isAfter(endDate))
                .map(absenceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AbsenceDTO> getAbsencesByMotif(String motif) {
        return absenceRepository.findByMotifContainingIgnoreCase(motif)
                .stream()
                .map(absenceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AbsenceDTO> getAbsencesByJustifiee(boolean justifiee) {
        return absenceRepository.findByJustifiee(justifiee)
                .stream()
                .map(absenceMapper::toDTO)
                .collect(Collectors.toList());
    }
}
