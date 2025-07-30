package uz.pdp.startupproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.startupproject.payload.CompanyDTO;
import uz.pdp.startupproject.payload.withoutId.CompanyDto;
import uz.pdp.startupproject.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public List<CompanyDTO> findAll() {
        List<CompanyDTO> all = companyService.findAll();
        return all;
    }

    @GetMapping("/{id}")
    public CompanyDTO getById(@PathVariable Long id) {
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




    @PutMapping("/update/{id}")
    public CompanyDTO update(@PathVariable Long id, @RequestBody CompanyDTO companyDTO) {
        CompanyDTO update = companyService.update(companyDTO);
        return update;
    }

}
