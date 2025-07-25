package uz.pdp.startupproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.startupproject.filter.SearchService;
import uz.pdp.startupproject.payload.ApiResult;
import uz.pdp.startupproject.payload.CompanyDTO;
import uz.pdp.startupproject.payload.withoutId.CompanyDto;
import uz.pdp.startupproject.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final SearchService searchService;

    @GetMapping
    public List<CompanyDTO> findAll() {
        List<CompanyDTO> all = companyService.findAll();
        return all;
    }

    @GetMapping("/{id}")
    public CompanyDTO findById(@PathVariable Long id) {
        CompanyDTO byId = companyService.findById(id);
        return byId;
    }

    @PostMapping("/create")
    public CompanyDTO create(@RequestBody CompanyDTO companyDto) {
        CompanyDTO save = companyService.save(companyDto);
        return save;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        CompanyDTO delete = companyService.delete(id);
        return true;
    }
 employee_branch

    @PutMapping("/{id}")
    public CompanyDTO update(@PathVariable Long id, @RequestBody CompanyDTO companyDto) {
        CompanyDTO update = companyService.update(companyDto);

    @PutMapping("/update/{id}")
    public CompanyDto update(@PathVariable Long id, @RequestBody CompanyDto companyDto) {
        CompanyDto update = companyService.update(companyDto);
 client
        return update;
    }


    @GetMapping("/search")
    public ApiResult<List<CompanyDTO>> search(@RequestParam String name) {
        List<CompanyDTO> companyDTOS = searchService.searchCompany(name);
        return ApiResult.success(companyDTOS);
    }
}
