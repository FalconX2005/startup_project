package uz.pdp.startupproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.startupproject.payload.ApiResult;
import uz.pdp.startupproject.payload.EmployeeDTO;
import uz.pdp.startupproject.service.EmployeeService;

import java.util.List;

/**
 * Created by: Umar
 * DateTime: 7/16/2025 4:42 PM
 */
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/read")
    public ApiResult<List<EmployeeDTO>> getAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/read/{id}")
    public ApiResult<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PostMapping("/create")
    public ApiResult<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }


    @PutMapping("/update/{id}")
    public ApiResult<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.update(id, employeeDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult<EmployeeDTO> deleteEmployee(@PathVariable Long id) {
        return employeeService.delete(id);
    }
}
