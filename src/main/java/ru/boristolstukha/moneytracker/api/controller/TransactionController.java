package ru.boristolstukha.moneytracker.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.boristolstukha.moneytracker.api.converter.TransactionConverter;
import ru.boristolstukha.moneytracker.api.dto.TransactionDTO;
import ru.boristolstukha.moneytracker.api.exception.HttpNotFoundException;
import ru.boristolstukha.moneytracker.entity.Transaction;
import ru.boristolstukha.moneytracker.repository.TransactionRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    final private TransactionRepository transactionRepository;
    final private TransactionConverter transactionConverter;
    @Autowired
    ObjectMapper objectMapper;

    public TransactionController(TransactionRepository transactionRepository,
                                 TransactionConverter transactionConverter) {
        this.transactionRepository = transactionRepository;
        this.transactionConverter = transactionConverter;
    }

    @GetMapping("")
    public Iterable<TransactionDTO> getTransactions() {
        Iterable<Transaction> transactions = transactionRepository.findAll();
        return transactionConverter.EntityListToDTOList(transactions);
    }

    @GetMapping("/{id}")
    public TransactionDTO getTransaction(@PathVariable(name = "id") Long id) throws HttpNotFoundException {
        Transaction transaction = findEntity(id);
        return transactionConverter.entityToDTO(transaction);
    }

    @RequestMapping(path = "", method = {RequestMethod.POST, RequestMethod.PUT})
    public TransactionDTO putTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = transactionConverter.DTOToEntity(transactionDTO);
        transactionRepository.save(transaction);
        return transactionConverter.entityToDTO(transaction);
    }

    @RequestMapping(path = "/{id}", method = {RequestMethod.POST, RequestMethod.PUT})
    public TransactionDTO updateTransaction(@Valid @RequestBody TransactionDTO transactionDTO,
                                            @PathVariable(name = "id") Long id) throws HttpNotFoundException {
        Transaction transaction = findEntity(id);
        transactionConverter.updateEntity(transaction, transactionDTO);
        transactionRepository.save(transaction);
        return transactionConverter.entityToDTO(transaction);
    }

    private Transaction findEntity(Long id) throws HttpNotFoundException {
        Transaction transaction = transactionRepository.findOne(id);
        if (transaction == null) {
            throw new HttpNotFoundException();
        }
        return transaction;
    }

}
