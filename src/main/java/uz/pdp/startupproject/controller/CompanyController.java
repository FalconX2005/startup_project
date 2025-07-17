package uz.pdp.startupproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.startupproject.payload.CompanyDto;
import uz.pdp.startupproject.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public List<CompanyDto> findAll() {
        List<CompanyDto> all = companyService.findAll();
        return all;
    }

    @GetMapping("/{id}")
    public CompanyDto findById(@PathVariable Long id) {
        CompanyDto byId = companyService.findById(id);
        return byId;
    }

    @PostMapping("/create")
    public CompanyDto create(@RequestBody CompanyDto companyDto) {
        CompanyDto save = companyService.save(companyDto);
        return save;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        CompanyDto delete = companyService.delete(id);
        return true;
    }
    @PutMapping("/update/{id}")
    public CompanyDto update(@PathVariable Long id, @RequestBody CompanyDto companyDto) {
        CompanyDto update = companyService.update(companyDto);
        return update;
    }

}
