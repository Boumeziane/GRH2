package com.rh.grh.service;

import com.rh.grh.dto.AbsenceDTO;

import java.time.LocalDate;
import java.util.List;

public interface AbsenceService {

    AbsenceDTO createAbsence(AbsenceDTO dto);

    AbsenceDTO updateAbsence(Long id, AbsenceDTO dto);

    void deleteAbsence(Long id);

    AbsenceDTO getAbsenceById(Long id);

    List<AbsenceDTO> getAllAbsences();

    List<AbsenceDTO> getAbsencesByEmployeeId(Long employeeId);

    List<AbsenceDTO> getAbsencesByDateRange(LocalDate startDate, LocalDate endDate);

    List<AbsenceDTO> getAbsencesByMotif(String motif);

    List<AbsenceDTO> getAbsencesByJustifiee(boolean justifiee);
}
