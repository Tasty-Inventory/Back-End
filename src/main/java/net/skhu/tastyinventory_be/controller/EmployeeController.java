package net.skhu.tastyinventory_be.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.entity.Employee;
import net.skhu.tastyinventory_be.service.EmployeeResponseDto;
import net.skhu.tastyinventory_be.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import net.skhu.tastyinventory_be.exception.model.EmployeeEdit;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("list")
    public ResponseEntity<List<EmployeeResponseDto>> findAll() {
        return ResponseEntity.ok(employeeService.getEmployee());
    }


    @PostMapping("create")
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
}