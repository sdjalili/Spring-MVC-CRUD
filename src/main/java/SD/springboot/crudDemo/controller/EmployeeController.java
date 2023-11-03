package SD.springboot.crudDemo.controller;

import SD.springboot.crudDemo.service.EmployeeService;
import SD.springboot.crudDemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") Integer employeeId, Model theModel){
        Employee employee = employeeService.findById(employeeId);
        theModel.addAttribute("employee", employee);
        return "employee/employee-form-add";
    }
    @PostMapping("/updateEmployee")
    public String updateEmployee(@ModelAttribute("employee") Employee theEmployee){
        employeeService.save(theEmployee);
        return "redirect:/employee/list";
    }
    @GetMapping("/removeEmployee")
    public String removeEmployee(@RequestParam("deleteId") Integer employeeId){
        employeeService.deleteById(employeeId);
        return "redirect:/employee/list";
    }
}
