package ru.boristolstukha.moneytracker.api.converter;

import org.springframework.stereotype.Service;
import ru.boristolstukha.moneytracker.api.dto.TransactionDTO;
import ru.boristolstukha.moneytracker.entity.Transaction;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TransactionConverter {

    public TransactionDTO entityToDTO(Transaction entity){
        TransactionDTO dto = new TransactionDTO();
        dto.id = entity.getId();
        dto.description = entity.getDescription();
        dto.amount = entity.getAmount();
        dto.date = new Date(entity.getDate().getTime());
        return dto;
    }

    public Transaction DTOToEntity(TransactionDTO dto){
        Transaction entity = new Transaction();
        return updateEntity(entity, dto);
    }

    public Transaction updateEntity(Transaction entity, TransactionDTO dto){
        entity.setDescription(dto.description);
        entity.setAmount(dto.amount);
        entity.setDate(dto.date);
        return entity;
    }

    public Iterable<TransactionDTO> EntityListToDTOList(Iterable<Transaction> entityList){
        return StreamSupport.stream(entityList.spliterator(), false)
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

}
