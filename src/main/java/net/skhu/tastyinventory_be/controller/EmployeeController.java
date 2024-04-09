package net.skhu.tastyinventory_be.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    @GetMapping("list")
    public ResponseEntity<List<EmployeeResponseDto>> findAll(){
        return ResponseEntity.ok(employeeService.getEmployee());
    }

    @GetMapping("create")
    public String create(Model model) {
        EmployeeEdit employeeEdit = new EmployeeEdit();
        model.addAttribute("employeeEdit", employeeEdit);
        return "employee/edit";
    }

    @PostMapping("create")
    public String create(Model model, @Valid EmployeeEdit employeeEdit,
                         BindingResult bindingResult) {
        return "redirect:/employee/list";
    }

    @GetMapping("edit")
    public String edit(Model model, int id) {
        model.addAttribute("employeeEdit", new EmployeeEdit());
        return "employee/edit";
    }

    @PostMapping(value = "edit", params = "cmd=save")
    public String edit(Model model, @Valid EmployeeEdit employeeEdit,
                       BindingResult bindingResult) {
        return "redirect:/employee/list";
    }

    @PostMapping(value = "edit", params = "cmd=delete")
    public String delete(Model model, EmployeeEdit employeeEdit) {
        return "redirect:/employee/list";
    }
}