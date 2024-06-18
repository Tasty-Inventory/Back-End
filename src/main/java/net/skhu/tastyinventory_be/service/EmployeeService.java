package net.skhu.tastyinventory_be.service;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.controller.employee.dto.EmployeeResponseDto;
import net.skhu.tastyinventory_be.domain.employee.Employee;
import net.skhu.tastyinventory_be.domain.employee.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Transactional
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Transactional
    public List<EmployeeResponseDto> getEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponseDto> employeeResponseDtos = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
            employeeResponseDto.setId(employee.getId());
            employeeResponseDto.setName(employee.getName());
            employeeResponseDto.setPhoneNumber(employee.getPhoneNumber());
            employeeResponseDto.setPosition(employee.getPosition());
            employeeResponseDto.setRrn(employee.getRrn());
            employeeResponseDto.setEmail(employee.getEmail());
            employeeResponseDto.setAddress(employee.getAddress());
            employeeResponseDto.setHireDate(employee.getHireDate() != null ? sdf.format(employee.getHireDate()) : null);
            employeeResponseDto.setEmploymentStatus(employee.getEmploymentStatus());
            employeeResponseDto.setBankAccount(employee.getBankAccount());
            employeeResponseDto.setNote(employee.getNote());
            employeeResponseDtos.add(employeeResponseDto);
        }
        return employeeResponseDtos;
    }

    @Transactional(readOnly = true)
    public EmployeeResponseDto getEmployeeDetails(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
            employeeResponseDto.setId(employee.getId());
            employeeResponseDto.setName(employee.getName());
            employeeResponseDto.setPhoneNumber(employee.getPhoneNumber());
            employeeResponseDto.setPosition(employee.getPosition());
            employeeResponseDto.setRrn(employee.getRrn());
            employeeResponseDto.setEmail(employee.getEmail());
            employeeResponseDto.setAddress(employee.getAddress());
            employeeResponseDto.setHireDate(employee.getHireDate() != null ? sdf.format(employee.getHireDate()) : null);
            employeeResponseDto.setEmploymentStatus(employee.getEmploymentStatus());
            employeeResponseDto.setBankAccount(employee.getBankAccount());
            employeeResponseDto.setNote(employee.getNote());
            return employeeResponseDto;
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }
}
