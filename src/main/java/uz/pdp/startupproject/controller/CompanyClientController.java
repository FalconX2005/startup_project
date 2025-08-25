package uz.pdp.startupproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.startupproject.payload.ApiResult;
import uz.pdp.startupproject.payload.ClientDTO;
import uz.pdp.startupproject.payload.CompanyClientDTO;
import uz.pdp.startupproject.payload.CompanyDTO;
import uz.pdp.startupproject.service.CompanyClientService;

import java.util.List;

@RestController
@RequestMapping("/companyClient")
@RequiredArgsConstructor
public class CompanyClientController {
    private final CompanyClientService companyClientService;

    @GetMapping("/{id}")
    public ApiResult<List<ClientDTO>> findAllClientsByCompanyId(@PathVariable Long companyId) {
        List<ClientDTO> allClients = companyClientService.findAllClients(companyId);
        return ApiResult.success(allClients);
    }

    @GetMapping("/{clientId}")
    public ApiResult<List<CompanyDTO>> findAllCompaniesByClientId(@PathVariable Long clientId) {
        List<CompanyDTO> companyByClientId = companyClientService.findCompanyByClientId(clientId);
        return ApiResult.success(companyByClientId);
    }
    @PostMapping("/add")
    public ApiResult<CompanyClientDTO> addClient(@RequestBody CompanyClientDTO companyClientDTO) {
        ApiResult<CompanyClientDTO> add = companyClientService.add(companyClientDTO);
        return add;
    }
    @DeleteMapping("/{id}")
    public ApiResult<CompanyClientDTO> deleteClient(@PathVariable Long id){
        ApiResult<CompanyClientDTO> delete = companyClientService.delete(id);
        return delete;
    }
}
