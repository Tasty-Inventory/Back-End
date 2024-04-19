package net.skhu.tastyinventory_be.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.tastyinventory_be.entity.Employee;
import net.skhu.tastyinventory_be.service.EmployeeResponseDto;
import net.skhu.tastyinventory_be.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import net.skhu.tastyinventory_be.exception.model.EmployeeEdit;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("employee")
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> findAll() {
        return ResponseEntity.ok(employeeService.getEmployee());
    }


    @PostMapping
    public ResponseEntity<String> createEmployee(@Valid @RequestBody EmployeeEdit employeeEdit, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("입력값이 올바르지 않습니다.");
        }

        Employee employee = new Employee();
        employee.setName(employeeEdit.getName());
        employee.setRrn(employeeEdit.getRrn());
        employee.setPhoneNumber(employeeEdit.getPhoneNumber());
        employee.setEmail(employeeEdit.getEmail());
        employee.setAddress(employeeEdit.getAddress());
        employee.setPosition(employeeEdit.getPosition());
        employee.setHireDate(employeeEdit.getHireDate());
        employee.setEmploymentStatus(employeeEdit.getEmploymentStatus());
        employee.setBankAccount(employeeEdit.getBankAccount());
        employee.setNote(employeeEdit.getNote());

        employeeService.save(employee);

        List<EmployeeResponseDto> updatedEmployeeList = employeeService.getEmployee();
        return ResponseEntity.ok("직원이 생성되었습니다.");
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeDetails(@PathVariable(name ="id") Long id) {
        EmployeeResponseDto employee = employeeService.getEmployeeDetails(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("{id}")
    public ResponseEntity<String> editEmployeeDetails(@PathVariable(name ="id") Long id, @Valid @RequestBody EmployeeEdit employeeEdit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("입력값이 올바르지 않습니다.");
        }

        Optional<Employee> employeeOptional = employeeService.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();

            employee.setName(employeeEdit.getName());
            employee.setRrn(employeeEdit.getRrn());
            employee.setPhoneNumber(employeeEdit.getPhoneNumber());
            employee.setEmail(employeeEdit.getEmail());
            employee.setAddress(employeeEdit.getAddress());
            employee.setPosition(employeeEdit.getPosition());
            employee.setHireDate(employeeEdit.getHireDate());
            employee.setEmploymentStatus(employeeEdit.getEmploymentStatus());
            employee.setBankAccount(employeeEdit.getBankAccount());
            employee.setNote(employeeEdit.getNote());

            employeeService.save(employee);
            return ResponseEntity.ok("직원 정보가 수정되었습니다.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(name ="id") Long id) {
        Optional<Employee> employeeOptional = employeeService.findById(id);
        if (employeeOptional.isPresent()) {
            employeeService.deleteById(id);
            return ResponseEntity.ok("직원 정보 삭제");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}