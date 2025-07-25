package uz.pdp.startupproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.startupproject.entity.Client;
import uz.pdp.startupproject.entity.User;
import uz.pdp.startupproject.payload.ClientDto;
import uz.pdp.startupproject.repository.ClientRepository;
import uz.pdp.startupproject.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    public List<ClientDto> getAll() {
        List<Client> all = clientRepository.findAll();

        if (all.isEmpty()) {
            throw new RuntimeException("Client not found");
        }
        List<ClientDto> clientDtos = new ArrayList<>();
        for (Client client : all) {
            ClientDto build = ClientDto.builder()
                    .id(client.getId())
                    .role(client.getUser().getRole())
                    .balance(client.getBalance())
                    .phoneNumber(client.getPhone())
                    .firstName(client.getFirstName())
                    .lastName(client.getLastName())
                    .build();
            clientDtos.add(build);
        }
        return clientDtos;
    }

    public ClientDto getById(Long id) {
        Optional<Client> byId = clientRepository.findById(id);

        if (!byId.isPresent()) {
            throw new RuntimeException("Client not found");
        }
        Client client = byId.get();
        ClientDto build = ClientDto.builder()
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .role(client.getUser().getRole())
                .balance(client.getBalance())
                .username(client.getUser().getUsername())
                .phoneNumber(client.getPhone())
                .id(client.getId())
                .build();
        return build;
    }

    public ClientDto save(ClientDto clientDto) {

        Optional<User> user = userRepository.findByUsername(clientDto.getUsername());
        if (user.isPresent()) {
            throw new RuntimeException("User already exists");
        }
        User build = User.builder()
                .username(clientDto.getUsername())
                .role(clientDto.getRole())
                .password(clientDto.getPassword())
                .build();
        User save = userRepository.save(build);

        Client result = Client.builder()
                .balance(clientDto.getBalance())
                .phone(clientDto.getPhoneNumber())
                .firstName(clientDto.getFirstName())
                .lastName(clientDto.getLastName())
                .user(save)
                .build();

        Client client = clientRepository.save(result);
        clientDto.setId(client.getId());
        return clientDto;
    }

    public ClientDto update(ClientDto clientDto) {

        Optional<Client> byId = clientRepository.findById(clientDto.getId());

        if (!byId.isPresent()) {
            throw new RuntimeException("Client not found");
        }
        Client client = byId.get();
        Optional<User> user = userRepository.findById(client.getUser().getId());

        if (!user.isPresent()) {
            throw new RuntimeException("User not found");

        }
        User user1 = user.get();
        user1.setPassword(clientDto.getPassword());
        user1.setRole(clientDto.getRole());
        user1.setUsername(clientDto.getUsername());
        User save = userRepository.save(user1);
        client.setBalance(clientDto.getBalance());
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setPhone(clientDto.getPhoneNumber());
        client.setUser(save);
        Client client1 = clientRepository.save(client);
        clientDto.setId(client1.getId());
        return clientDto;

    }

    public ClientDto delete(Long id) {
        Optional<Client> byId = clientRepository.findById(id);
        if (!byId.isPresent()) {
            throw new RuntimeException("client not found ");
        }
        Client client = byId.get();
        Optional<User> byId1 = userRepository.findById(client.getUser().getId());
        if (!byId1.isPresent()) {
            throw new RuntimeException("User not found");
        }
        User user1 = byId1.get();
        userRepository.delete(user1);
        clientRepository.delete(client);
        ClientDto build = ClientDto.builder()
                .id(client.getId())
                .role(client.getUser().getRole())
                .balance(client.getBalance())
                .username(client.getUser().getUsername())
                .phoneNumber(client.getPhone())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .build();

        return build;
    }

}

































