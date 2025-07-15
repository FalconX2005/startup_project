package uz.pdp.startupproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.startupproject.entity.Company;
import uz.pdp.startupproject.payload.CompanyDto;
import uz.pdp.startupproject.repository.CompanyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    @Autowired
    private  final CompanyRepository companyRepository;

    public   List<CompanyDto> findAll() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyDto> companiesDto = new ArrayList<>();
        if (companies.isEmpty()) {
            throw new RuntimeException("No companies found");
        }
        for (Company company : companies) {
            if(company.isDeleted()){
                continue;
            }
            CompanyDto build = CompanyDto.builder()
                    .id(company.getId())
                    .name(company.getName())
                    .email(company.getEmail())
                    .phone(company.getPhone())
                    .address(company.getLocation())
                    .build();
            companiesDto.add(build);
        }
        return companiesDto;
    }

    public  CompanyDto findById(Long id) {
        Optional<Company> byId = companyRepository.findById(id);
        if (!byId.isPresent()) {
            throw new  RuntimeException("Company not found");
        }
        Company company = byId.get();
        CompanyDto builder = CompanyDto.builder()
                .email(company.getEmail())
                .name(company.getName())
                .phone(company.getPhone())
                .address(company.getLocation())
                .build();
        return builder;
    }

    public   CompanyDto save(CompanyDto companyDto) {
        List<Company> byName = companyRepository.findByName(companyDto.getName());
        if (!byName.isEmpty()) {
            throw new RuntimeException("Company  already exists");
        }
        Company build = Company.builder()
                .name(companyDto.getName())
                .email(companyDto.getEmail())
                .location(companyDto.getAddress())
                .phone(companyDto.getPhone())
                .build();
        companyRepository.save(build);
        companyDto.setId(build.getId());
        return companyDto;
    }
    public   CompanyDto update(CompanyDto companyDto) {
        Optional<Company> byId = companyRepository.findById(companyDto.getId());
        if (!byId.isPresent()) {
            throw new RuntimeException("Company not found");
        }
        Company company = byId.get();
        company.setEmail(companyDto.getEmail());
        company.setPhone(companyDto.getPhone());
        company.setLocation(companyDto.getAddress());
        company.setName(companyDto.getName());
        companyRepository.save(company);
        companyDto.setId(company.getId());
        return companyDto;
    }

    public   CompanyDto delete(Long id) {
        Optional<Company> byId = companyRepository.findById(id);

        if (!byId.isPresent()) {
            throw new RuntimeException("Company not found");
        }
        Company company = byId.get();
        companyRepository.delete(company);
        CompanyDto build = CompanyDto.builder()
                .address(company.getLocation())
                .name(company.getName())
                .email(company.getEmail())
                .phone(company.getPhone())
                .build();

        return build;
    }


}
