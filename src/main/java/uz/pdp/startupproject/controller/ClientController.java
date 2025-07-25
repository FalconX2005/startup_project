package uz.pdp.startupproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.startupproject.payload.ClientDto;
import uz.pdp.startupproject.service.ClientService;

import java.util.List;
@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public List<ClientDto>getAllClients() {
        List<ClientDto> allClients = clientService.getAll();
        return allClients;
    }

    @GetMapping("/{id}")
    public ClientDto getClientById(@PathVariable Long id) {
        ClientDto clientById = clientService.getById(id);
        return clientById;
    }

    @PostMapping("/create")
    public ClientDto createClient(@RequestBody ClientDto client) {
        ClientDto client1 = clientService.save(client);
        return client1;
    }
    @PutMapping("/update/{id}")
    public ClientDto updateClient(@PathVariable Long id ,@RequestBody ClientDto client) {
        ClientDto update = clientService.update(client);
        return update;
    }

    @DeleteMapping("/{id}")
    public ClientDto deleteClient(@PathVariable Long id) {
        ClientDto delete = clientService.delete(id);
        return delete;

    }

}
