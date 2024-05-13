package net.skhu.tastyinventory_be.controller.employee;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.tastyinventory_be.common.dto.BaseResponse;
import net.skhu.tastyinventory_be.domain.employee.Employee;
import net.skhu.tastyinventory_be.controller.employee.dto.EmployeeResponseDto;
import net.skhu.tastyinventory_be.exception.ErrorCode;
import net.skhu.tastyinventory_be.exception.SuccessCode;
import net.skhu.tastyinventory_be.exception.model.NotFoundException;
import net.skhu.tastyinventory_be.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import net.skhu.tastyinventory_be.controller.employee.dto.EmployeeEdit;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("employee")
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<EmployeeResponseDto>>> findAll() {
        return ResponseEntity.ok(BaseResponse.success(SuccessCode.GET_SUCCESS, employeeService.getEmployee()));
    }


    @PostMapping
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeEdit employeeEdit) {

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

        return ResponseEntity.ok(BaseResponse.success(SuccessCode.EMPLOYEE_CREATE_SUCCESS));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getEmployeeDetails(@PathVariable(name ="id") Long id) {
        EmployeeResponseDto employee = employeeService.getEmployeeDetails(id);
        if (employee != null) {
            return ResponseEntity.ok(BaseResponse.success(SuccessCode.GET_SUCCESS, employee));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> editEmployeeDetails(@PathVariable(name ="id") Long id, @Valid @RequestBody EmployeeEdit employeeEdit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("입력값이 올바르지 않습니다.");
        }

        Optional<Employee> employeeOptional = employeeService.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();

            if (employeeEdit.getName() != null) {
                employee.setName(employeeEdit.getName());
            }
            if (employeeEdit.getRrn() != null) {
                employee.setRrn(employeeEdit.getRrn());
            }
            if (employeeEdit.getPhoneNumber() != null) {
                employee.setPhoneNumber(employeeEdit.getPhoneNumber());
            }
            if (employeeEdit.getEmail() != null) {
                employee.setEmail(employeeEdit.getEmail());
            }
            if (employeeEdit.getAddress() != null) {
                employee.setAddress(employeeEdit.getAddress());
            }
            if (employeeEdit.getPosition() != null) {
                employee.setPosition(employeeEdit.getPosition());
            }
            if (employeeEdit.getHireDate() != null) {
                employee.setHireDate(employeeEdit.getHireDate());
            }
            if (employeeEdit.getEmploymentStatus() != null) {
                employee.setEmploymentStatus(employeeEdit.getEmploymentStatus());
            }
            if (employeeEdit.getBankAccount() != null) {
                employee.setBankAccount(employeeEdit.getBankAccount());
            }
            if (employeeEdit.getNote() != null) {
                employee.setNote(employeeEdit.getNote());
            }


            employeeService.save(employee);
            return ResponseEntity.ok(BaseResponse.success(SuccessCode.EMPLOYEE_UPDATE_SUCCESS));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(name ="id") Long id) {
        employeeService.findById(id).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()
        ));
        employeeService.deleteById(id);
        return ResponseEntity.ok(BaseResponse.success(SuccessCode.EMPLOYEE_DELETE_SUCCESS));
    }
}