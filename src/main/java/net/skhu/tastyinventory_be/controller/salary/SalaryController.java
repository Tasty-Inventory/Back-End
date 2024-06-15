package net.skhu.tastyinventory_be.controller.salary;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.tastyinventory_be.common.dto.BaseResponse;
import net.skhu.tastyinventory_be.domain.salary.Salary;
import net.skhu.tastyinventory_be.controller.salary.dto.SalaryResponseDto;
import net.skhu.tastyinventory_be.exception.ErrorCode;
import net.skhu.tastyinventory_be.exception.SuccessCode;
import net.skhu.tastyinventory_be.service.SalaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import net.skhu.tastyinventory_be.controller.salary.dto.SalaryEdit;

import java.util.List;
import java.util.Optional;




@RequiredArgsConstructor
@RestController
@RequestMapping("salary")
@Slf4j
public class SalaryController {

    private final SalaryService salaryService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<SalaryResponseDto>>>findAll() {
        return ResponseEntity.ok(BaseResponse.success(SuccessCode.GET_SUCCESS, salaryService.getSalary()));
    }


    @PostMapping
    public ResponseEntity<?> createSalary(@Valid @RequestBody SalaryEdit salaryEdit) {

        Salary salary = new Salary();
        salary.setName(salaryEdit.getName());
        salary.setPosition(salaryEdit.getPosition());
        salary.setBaseSalary(salaryEdit.getBaseSalary());

        salaryService.save(salary);

        return ResponseEntity.ok(BaseResponse.success(SuccessCode.SALARY_CREATE_SUCCESS));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getSalaryDetails(@PathVariable(name ="id") Long id) {
        SalaryResponseDto salary = salaryService.getSalaryDetails(id);
        if (salary != null) {
            return ResponseEntity.ok(BaseResponse.success(SuccessCode.GET_SUCCESS, salary));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> editSalaryDetails(@PathVariable(name ="id") Long id, @Valid @RequestBody SalaryEdit salaryEdit) {


        Optional<Salary> salaryOptional = salaryService.findById(id);
        if (salaryOptional.isPresent()) {
            Salary salary = salaryOptional.get();

            if (salaryEdit.getName() != null) {
                salary.setName(salaryEdit.getName());
            }
            if (salaryEdit.getPosition() != null) {
                salary.setPosition(salaryEdit.getPosition());
            }
            if (salaryEdit.getBaseSalary() != null) {
                salary.setBaseSalary(salaryEdit.getBaseSalary());
            }


            salaryService.save(salary);
            return ResponseEntity.ok(BaseResponse.success(SuccessCode.SALARY_PATCH_SUCCESS));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponse.error(ErrorCode.NOT_FOUND_STAFF_EXCEPTION));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSalary(@PathVariable(name ="id") Long id) {
        Optional<Salary> salaryOptional = salaryService.findById(id);
        if (salaryOptional.isPresent()) {
            salaryService.deleteById(id);
            return ResponseEntity.ok(BaseResponse.success(SuccessCode.SALARY_DELETE_SUCCESS));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponse.error(ErrorCode.NOT_FOUND_STAFF_EXCEPTION));
        }
    }
}