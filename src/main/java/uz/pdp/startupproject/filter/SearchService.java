package uz.pdp.startupproject.filter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.startupproject.entity.Client;
import uz.pdp.startupproject.entity.Company;
import uz.pdp.startupproject.entity.Employee;
import uz.pdp.startupproject.exception.RestException;
import uz.pdp.startupproject.payload.ClientDTO;
import uz.pdp.startupproject.payload.CompanyDTO;
import uz.pdp.startupproject.payload.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by: Umar
 * DateTime: 7/25/2025 3:59 PM
 */
@Service
@RequiredArgsConstructor
public class SearchService {

    private final EntityManager entityManager;


    public List<CompanyDTO> searchCompany(String companyName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Company> criteriaQuery = criteriaBuilder.createQuery(Company.class);
        Root<Company> from = criteriaQuery.from(Company.class);
        List<Predicate> predicates = new ArrayList<>();

        Predicate name = criteriaBuilder.like(criteriaBuilder.lower(from.get("name")), "%" + companyName.toLowerCase() + "%");
        predicates.add(name);

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        List<Company> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        if(resultList.isEmpty()) {
            throw RestException.error("Company not found");
        }

       return resultList
               .stream()
               .map(this::convertCompanyToDto)
               .collect(Collectors.toList());
    }

    private CompanyDTO convertCompanyToDto(Company company) {
        return CompanyDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .address(company.getLocation())
                .phone(company.getPhone())
                .email(company.getEmail())
                .build();
    }

    public List<ClientDTO> searchClient(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        Root<Client> from = criteriaQuery.from(Client.class);
        List<Predicate> predicates = new ArrayList<>();

        Predicate firstName = criteriaBuilder.like(criteriaBuilder.lower(from.get("firstName")), "%" + name.toLowerCase() + "%");
        Predicate lastName = criteriaBuilder.like(criteriaBuilder.lower(from.get("lastName")), "%" + name.toLowerCase() + "%");

        Predicate predicate = criteriaBuilder.or(firstName, lastName);
        predicates.add(predicate);

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        List<Client> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        if(resultList.isEmpty()) {
            throw RestException.error("Client not found");
        }

        return resultList
                .stream()
                .map(this::convertClientToDto)
                .collect(Collectors.toList());
    }

    private ClientDTO convertClientToDto(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .phoneNumber(client.getPhone())
                .balance(client.getBalance())
                .username(client.getUser().getUsername())
                .password(client.getUser().getPassword())
                .role(client.getUser().getRole())
                .build();
    }

    public List<EmployeeDTO> searchEmployee(String name){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> from = criteriaQuery.from(Employee.class);
        List<Predicate> predicates = new ArrayList<>();

        Predicate firstName = criteriaBuilder.like(criteriaBuilder.lower(from.get("firstName")), "%" + name.toLowerCase() + "%");
        Predicate lastName = criteriaBuilder.like(criteriaBuilder.lower(from.get("lastName")), "%" + name.toLowerCase() + "%");

        Predicate predicate = criteriaBuilder.or(firstName, lastName);
        predicates.add(predicate);

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        List<Employee> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        if(resultList.isEmpty()) {
            throw RestException.error("Employee not found");
        }

        return resultList
                .stream()
                .map(this::convertEmployeeToDto)
                .collect(Collectors.toList());

    }

    private EmployeeDTO convertEmployeeToDto(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .phoneNumber(employee.getPhoneNumber())
                .attachmentId(employee.getAttachment().getId())
                .companyId(employee.getCompany().getId())
                .gender(employee.getGender())
                .build();
    }
}