package net.skhu.tastyinventory_be.service;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.controller.salary.dto.SalaryResponseDto;
import net.skhu.tastyinventory_be.domain.salary.Salary;
import net.skhu.tastyinventory_be.domain.salary.SalaryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SalaryService {
    private final SalaryRepository salaryRepository;

    @Transactional
    public void save(Salary salary) {
        salaryRepository.save(salary);
    }

    @Transactional
    public List<SalaryResponseDto> getSalary(){
        List<Salary> salaries = salaryRepository.findAll();
        List<SalaryResponseDto> salaryResponseDtos = new ArrayList<>();
        for (Salary salary : salaries) {
            SalaryResponseDto salaryResponseDto = new SalaryResponseDto();
            salaryResponseDto.setId(salary.getId());
            salaryResponseDto.setName(salary.getName());
            salaryResponseDto.setBaseSalary(salary.getBaseSalary());
            salaryResponseDto.setPosition(salary.getPosition());
            salaryResponseDtos.add(salaryResponseDto);
        }
        return salaryResponseDtos;
    }

    @Transactional(readOnly = true)
    public SalaryResponseDto getSalaryDetails(Long id) {
        Optional<Salary> salaryOptional = salaryRepository.findById(id);
        if (salaryOptional.isPresent()) {
            Salary salary = salaryOptional.get();
            SalaryResponseDto salaryResponseDto = new SalaryResponseDto();
            salaryResponseDto.setId(salary.getId());
            salaryResponseDto.setName(salary.getName());
            salaryResponseDto.setBaseSalary(salary.getBaseSalary());
            salaryResponseDto.setPosition(salary.getPosition());
            // 나머지 필드도 필요에 따라 추가

            return salaryResponseDto;
        } else {
            return null;
        }
    }
    @Transactional
    public void deleteById(Long id) {
        salaryRepository.deleteById(id);
    }
    @Transactional
    public Optional<Salary> findById(Long id) {
        return salaryRepository.findById(id);
    }
}

