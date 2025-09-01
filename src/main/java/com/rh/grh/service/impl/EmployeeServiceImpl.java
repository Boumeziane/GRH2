package com.rh.grh.service.impl;

import com.rh.grh.dto.EmployeeDTO;
import com.rh.grh.entity.Account;
import com.rh.grh.entity.Employee;
import com.rh.grh.mapper.EmployeeMapper;
import com.rh.grh.repository.EmployeeRepository;
import com.rh.grh.repository.AccountRepository;
import com.rh.grh.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
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
            employee.setAccount(existingAccount); // attach managed entity
        } else {
            employee.setAccount(null); // no account
        }

        Employee saved = employeeRepository.save(employee);
        return employeeMapper.toDTO(saved);
    }


    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Mise à jour via MapStruct
        employeeMapper.updateEntityFromDTO(dto, existing);

        Employee updated = employeeRepository.save(existing);
        return employeeMapper.toDTO(updated);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return employeeMapper.toDTO(employee);
    }

    @Override
    public EmployeeDTO getEmployeeByAccountId(Long accountId) {
        Employee employee = employeeRepository.findByAccountId(accountId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return employeeMapper.toDTO(employee);
    }
}
