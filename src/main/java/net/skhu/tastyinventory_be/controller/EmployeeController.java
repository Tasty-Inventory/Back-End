package net.skhu.tastyinventory_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import java.util.List;
import net.skhu.tastyinventory_be.service.EmployeeService;
import net.skhu.tastyinventory_be.service.ScheduleService;
import net.skhu.tastyinventory_be.entity.Employee;
import net.skhu.tastyinventory_be.model.EmployeeEdit;
import net.skhu.tastyinventory_be.model.Schedule;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    ScheduleService scheduleService;

    @GetMapping("list")
    public String list(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "employee/list";
    }

    @GetMapping("create")
    public String create(Model model) {
        EmployeeEdit employeeEdit = new EmployeeEdit();
        List<Schedule> schedules = scheduleService.findAll();
        model.addAttribute("employeeEdit", employeeEdit);
        model.addAttribute("schedules", schedules);
        return "employee/edit";
    }

    @PostMapping("create")
    public String create(Model model, @Valid EmployeeEdit employeeEdit,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("schedules", scheduleService.findAll());
            return "employee/edit";
        }
        employeeService.insert(employeeEdit);
        return "redirect:/employee/list";
    }

    @GetMapping("edit")
    public String edit(Model model, int id) {
        EmployeeEdit employeeEdit = employeeService.findOne(id);
        List<Schedule> schedules = scheduleService.findAll();
        model.addAttribute("employeeEdit", employeeEdit);
        model.addAttribute("schedules", schedules);
        return "employee/edit";
    }

    @PostMapping(value = "edit", params = "cmd=save")
    public String edit(Model model, @Valid EmployeeEdit employeeEdit,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("schedules", scheduleService.findAll());
            return "employee/edit";
        }
        employeeService.update(employeeEdit);
        return "redirect:/employee/list";
    }

    @PostMapping(value = "edit", params = "cmd=delete")
    public String delete(Model model, EmployeeEdit employeeEdit) {
        employeeService.delete(employeeEdit.getId());
        return "redirect:/employee/list";
    }
}