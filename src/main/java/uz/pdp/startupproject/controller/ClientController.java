package uz.pdp.startupproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.startupproject.filter.SearchService;
import uz.pdp.startupproject.payload.ApiResult;
import uz.pdp.startupproject.payload.ClientDTO;
import uz.pdp.startupproject.service.ClientService;

import java.util.List;
@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final SearchService searchService;

    @GetMapping("/getAll")
    public List<ClientDTO>getAllClients() {
        List<ClientDTO> allClients = clientService.getAll();
        return allClients;
    }

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable Long id) {
        ClientDTO clientById = clientService.getById(id);
        return clientById;
    }

    @PostMapping("/create")
    public ClientDTO createClient(@RequestBody ClientDTO client) {
        ClientDTO client1 = clientService.save(client);
        return client1;
    }
    @PutMapping("/update/{id}")
    public ClientDTO updateClient(@PathVariable Long id , @RequestBody ClientDTO client) {
        ClientDTO update = clientService.update(client);
        return update;
    }

    @DeleteMapping("/{id}")
    public ClientDTO deleteClient(@PathVariable Long id) {
        ClientDTO delete = clientService.delete(id);
        return delete;
    }

    @GetMapping("/search")
    public ApiResult<List<ClientDTO>> searchClient(@RequestParam String name) {
        List<ClientDTO> clientDTOS = searchService.searchClient(name);
        return ApiResult.success(clientDTOS);
    }

}
