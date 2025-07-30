package uz.pdp.startupproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.startupproject.entity.Client;
import uz.pdp.startupproject.entity.Debts;
import uz.pdp.startupproject.exception.RestException;
import uz.pdp.startupproject.payload.ApiResult;
import uz.pdp.startupproject.payload.DebtsDTO;
import uz.pdp.startupproject.payload.withoutId.DebtsDto;
import uz.pdp.startupproject.repository.ClientRepository;
import uz.pdp.startupproject.repository.DebtsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DebtsService {

    private DebtsRepository debtsRepository;
    private ClientRepository clientRepository;

    public ApiResult<DebtsDTO> findById(Long id) {
        Optional<Debts> byId = debtsRepository.findById(id);
        if (!byId.isPresent()) {
            throw RestException.notFound("object doesn't exists", id);
        }
        Debts debts = byId.get();
        DebtsDTO build = DebtsDTO.builder()
                .id(debts.getId())
                .debtAmount(debts.getDebtAmount())
                .clientId(debts.getClient().getId())
                .toDate(debts.getToDate())
                .fromDate(debts.getFromDate())
                .priority(debts.getPriority())
                .build();
        return ApiResult.success(build);

    }

    public ApiResult<List<DebtsDTO>> findAll() {
        List<Debts> all = debtsRepository.findAll();
        List<DebtsDTO> result = new ArrayList<>();
        if (all.isEmpty()) {
            throw RestException.notFound("object doesn't exists", all.size());
        }
        for (Debts debts : all) {

            result.add(DebtsDTO.builder()
                    .id(debts.getId())
                    .debtAmount(debts.getDebtAmount())
                    .clientId(debts.getClient().getId())
                    .toDate(debts.getToDate())
                    .fromDate(debts.getFromDate())
                    .priority(debts.getPriority())
                    .build());
        }
        return ApiResult.success(result);
    }

    public ApiResult<DebtsDTO> save(DebtsDto dto) {
        Optional<Client> byId = clientRepository.findById(dto.getClientId());
        if (!byId.isPresent()) {
            throw RestException.notFound("client not found", dto.getClientId());
        }
        Client client = byId.get();

        Debts build = Debts.builder()
                .priority(dto.getPriority())
                .debtAmount(dto.getDebtAmount())
                .toDate(dto.getToDate())
                .fromDate(dto.getFromDate())
                .client(client).build();
        Debts debts = debtsRepository.save(build);

        return ApiResult.success(DebtsDTO.builder()
                .id(debts.getId())
                .fromDate(debts.getFromDate())
                .toDate(debts.getToDate())
                .priority(debts.getPriority())
                .clientId(client.getId())
                .debtAmount(debts.getDebtAmount())
                .build()
        );
    }

    public ApiResult<DebtsDTO> update(Long id,DebtsDto dto) {
        Optional<Client> byId = clientRepository.findById(dto.getClientId());

        if (!byId.isPresent()) {
            throw RestException.notFound("client not found", dto.getClientId());
        }
        Client client = byId.get();
        Optional<Debts> debtsOptional = debtsRepository.findById(id);
        if (!debtsOptional.isPresent()) {
            throw RestException.notFound("object doesn't exists", id);
        }
        Debts debts = debtsOptional.get();
        debts.setDebtAmount(dto.getDebtAmount());
        debts.setPriority(dto.getPriority());
        debts.setClient(client);
        debts.setToDate(dto.getToDate());
        debts.setFromDate(dto.getFromDate());
        Debts save = debtsRepository.save(debts);

        return ApiResult.success(DebtsDTO.builder()
                .id(save.getId())
                .debtAmount(save.getDebtAmount())
                .toDate(save.getToDate())
                .fromDate(save.getFromDate())
                .priority(save.getPriority())
                .clientId(save.getClient().getId())
                .build());
    }
    public ApiResult<DebtsDTO> delete(Long id) {
        Debts debts = debtsRepository.findById(id).orElseThrow(() ->
                RestException.notFound("object doesn't exists", id));

        debtsRepository.delete(debts);
        return ApiResult.success(DebtsDTO.builder()
                .id(debts.getId())
                .debtAmount(debts.getDebtAmount())
                .toDate(debts.getToDate())
                .fromDate(debts.getFromDate())
                .priority(debts.getPriority())
                .clientId(debts.getClient().getId())
                .build()
        );
    }
}
