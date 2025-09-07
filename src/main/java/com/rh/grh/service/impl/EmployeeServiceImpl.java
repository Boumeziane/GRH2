package com.rh.grh.service.impl;

import com.rh.grh.dto.EmployeeDTO;
import com.rh.grh.entity.Account;
import com.rh.grh.entity.Employee;
import com.rh.grh.mapper.EmployeeMapper;
import com.rh.grh.repository.EmployeeRepository;
import com.rh.grh.repository.AccountRepository;
import com.rh.grh.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AccountRepository accountRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        Employee employee = employeeMapper.toEntity(dto);

        // Check if account ID is provided
        if (dto.getAccountId() != null) {
            Account existingAccount = accountRepository.findById(dto.getAccountId())
                    .orElseThrow(() -> new RuntimeException("Account not found with id: " + dto.getAccountId()));
            employee.setAccount(existingAccount);
        } else {
            employee.setAccount(null);
        }

        Employee saved = employeeRepository.save(employee);
        log.info("Employé créé avec succès - ID: {}, Nom: {} {}", saved.getId(), saved.getPrenom(), saved.getNom());
        return employeeMapper.toDTO(saved);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        log.info("Mise à jour employé ID: {} avec données: {}", id, dto);

        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        log.debug("Employé existant avant MAJ - Account: {}, Leaves: {}, Absences: {}",
                existing.getAccount() != null ? existing.getAccount().getId() : "null",
                existing.getLeaves() != null ? existing.getLeaves().size() : 0,
                existing.getAbsences() != null ? existing.getAbsences().size() : 0);

        // SOLUTION 1: Utiliser la méthode MapStruct standard (si elle fonctionne)
        try {
            employeeMapper.updateEntityFromDTO(dto, existing);
        } catch (Exception e) {
            log.warn("Échec MapStruct standard, utilisation de la méthode manuelle: {}", e.getMessage());
            // SOLUTION 2: Fallback vers la méthode manuelle
            employeeMapper.updateEmployeeFields(dto, existing);
        }

        Employee updated = employeeRepository.save(existing);

        log.info("Employé mis à jour avec succès - ID: {}, Nom: {} {}",
                updated.getId(), updated.getPrenom(), updated.getNom());
        log.debug("Relations préservées - Account: {}, Leaves: {}, Absences: {}",
                updated.getAccount() != null ? updated.getAccount().getId() : "null",
                updated.getLeaves() != null ? updated.getLeaves().size() : 0,
                updated.getAbsences() != null ? updated.getAbsences().size() : 0);

        return employeeMapper.toDTO(updated);
    }

    @Override
    public void deleteEmployee(Long id) {
        log.info("Suppression employé ID: {}", id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        log.info("Suppression de l'employé: {} {}", employee.getPrenom(), employee.getNom());
        employeeRepository.deleteById(id);
        log.info("Employé supprimé avec succès - ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAllEmployees() {
        log.debug("Récupération de tous les employés");

        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOs = employees.stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());

        log.info("Récupération de {} employés", employeeDTOs.size());
        return employeeDTOs;
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDTO getEmployeeById(Long id) {
        log.debug("Récupération employé ID: {}", id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        EmployeeDTO dto = employeeMapper.toDTO(employee);
        log.debug("Employé trouvé: {} {}", dto.getPrenom(), dto.getNom());

        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDTO getEmployeeByAccountId(Long accountId) {
        log.debug("Récupération employé par Account ID: {}", accountId);

        Employee employee = employeeRepository.findByAccountId(accountId)
                .orElseThrow(() -> new RuntimeException("Employee not found for account id: " + accountId));

        EmployeeDTO dto = employeeMapper.toDTO(employee);
        log.debug("Employé trouvé par Account ID: {} {}", dto.getPrenom(), dto.getNom());

        return dto;
    }
}