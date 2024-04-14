package net.skhu.tastyinventory_be.service;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.entity.Employee;
import net.skhu.tastyinventory_be.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Transactional
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Transactional
    public List<EmployeeResponseDto> getEmployee(){
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponseDto> employeeResponseDtos = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
            employeeResponseDto.setId(employee.getId());
            employeeResponseDto.setName(employee.getName());
            employeeResponseDto.setPhoneNumber(employee.getPhoneNumber());
            employeeResponseDto.setPosition(employee.getPosition());
            employeeResponseDtos.add(employeeResponseDto);
        }
        return employeeResponseDtos;
    }
}
