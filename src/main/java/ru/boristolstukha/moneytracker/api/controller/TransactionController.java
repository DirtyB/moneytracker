package ru.boristolstukha.moneytracker.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import ru.boristolstukha.moneytracker.api.converter.TransactionConverter;
import ru.boristolstukha.moneytracker.api.dto.TransactionDTO;
import ru.boristolstukha.moneytracker.api.exception.HttpNotFoundException;
import ru.boristolstukha.moneytracker.data.entity.Transaction;
import ru.boristolstukha.moneytracker.data.repository.TransactionRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final TransactionConverter transactionConverter;
    @Autowired
    ObjectMapper objectMapper;

    public TransactionController(TransactionRepository transactionRepository,
                                 TransactionConverter transactionConverter) {
        this.transactionRepository = transactionRepository;
        this.transactionConverter = transactionConverter;
    }

    @GetMapping("")
    public Iterable<TransactionDTO> getTransactions(@PageableDefault(size = 4) Pageable pageable) {
        System.out.println(pageable);
        Iterable<Transaction> transactions = transactionRepository.findAll(pageable);
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

    @RequestMapping(path = "/{id}", method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH})
    public TransactionDTO updateTransaction(@Valid @RequestBody TransactionDTO transactionDTO,
                                            @PathVariable(name = "id") Long id) throws HttpNotFoundException {
        Transaction transaction = findEntity(id);
        transactionConverter.updateEntity(transaction, transactionDTO);
        transactionRepository.save(transaction);
        return transactionConverter.entityToDTO(transaction);
    }

    @RequestMapping(path = "/{id}", method = {RequestMethod.DELETE})
    public void deleteTransaction(@PathVariable(name = "id") Long id) throws HttpNotFoundException {
        Transaction transaction = findEntity(id);
        transactionRepository.delete(transaction);
    }

    private Transaction findEntity(Long id) throws HttpNotFoundException {
        Transaction transaction = transactionRepository.findOne(id);
        if (transaction == null) {
            throw new HttpNotFoundException("Transaction with ID " + id + " does not exist");
        }
        return transaction;
    }

}
