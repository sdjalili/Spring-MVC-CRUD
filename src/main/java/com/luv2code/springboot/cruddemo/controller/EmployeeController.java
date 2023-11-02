package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService theEmployeeService){
        this.employeeService = theEmployeeService;
    }
    @GetMapping("/list")
    public String getEmployeeList(Model tModel){
        List<Employee> employees = employeeService.findAll();
        tModel.addAttribute("employees", employees);
        return "employee/employee-list";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAddEmployee(Model thModel){
        Employee employee = new Employee();
        thModel.addAttribute("employee", employee);
        return "employee/employee-form-add";
    }
    @PostMapping("/addNewEmployee")
    public String save(@ModelAttribute("employee") Employee theEmployee){
        employeeService.save(theEmployee);
        return "redirect:/employee/list";
    }
}
