package net.skhu.tastyinventory_be.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.tastyinventory_be.entity.Salary;
import net.skhu.tastyinventory_be.controller.dto.SalaryResponseDto;
import net.skhu.tastyinventory_be.service.SalaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import net.skhu.tastyinventory_be.controller.dto.SalaryEdit;

import java.util.List;
import java.util.Optional;

// BOOLEAN 추가/삭제


@RequiredArgsConstructor
@RestController
@RequestMapping("salary")
@Slf4j
public class SalaryController {

    private final SalaryService salaryService;

    @GetMapping
    public ResponseEntity<List<SalaryResponseDto>> findAll() {
        return ResponseEntity.ok(salaryService.getSalary());
    }


    @PostMapping("{id}")
    public ResponseEntity<String> createSalary(@Valid @RequestBody SalaryEdit salaryEdit, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("입력값이 올바르지 않습니다.");
        }

        Salary salary = new Salary();
        salary.setName(salaryEdit.getName());
        salary.setPosition(salaryEdit.getPosition());
        salary.setBaseSalary(salaryEdit.getBaseSalary());

        salaryService.save(salary);

        List<SalaryResponseDto> updatedSalaryList = salaryService.getSalary();
        return ResponseEntity.ok("급여 정보 등록");
    }

    @GetMapping("{id}")
    public ResponseEntity<SalaryResponseDto> getSalaryDetails(@PathVariable(name ="id") Long id) {
        SalaryResponseDto salary = salaryService.getSalaryDetails(id);
        if (salary != null) {
            return ResponseEntity.ok(salary);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<String> editSalaryDetails(@PathVariable(name ="id") Long id, @Valid @RequestBody SalaryEdit salaryEdit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("입력값이 올바르지 않습니다.");
        }

        Optional<Salary> salaryOptional = salaryService.findById(id);
        if (salaryOptional.isPresent()) {
            Salary salary = salaryOptional.get();

            if (salaryEdit.getName() != null) {
                salary.setName(salaryEdit.getName());
            }
            if (salaryEdit.getPosition() != null) {
                salary.setName(salaryEdit.getPosition());
            }
            if (salaryEdit.getBaseSalary() != null) {
                salary.setBaseSalary(salaryEdit.getBaseSalary());
            }


            salaryService.save(salary);
            return ResponseEntity.ok("급여 정보 수정");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSalary(@PathVariable(name ="id") Long id) {
        Optional<Salary> salaryOptional = salaryService.findById(id);
        if (salaryOptional.isPresent()) {
            salaryService.deleteById(id);
            return ResponseEntity.ok("급여 정보 삭제");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}