package ru.boristolstukha.moneytracker.api.controller;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.boristolstukha.moneytracker.api.converter.TransactionConverter;
import ru.boristolstukha.moneytracker.api.dto.TransactionDTO;
import ru.boristolstukha.moneytracker.api.exception.HttpNotFoundException;
import ru.boristolstukha.moneytracker.entity.Transaction;
import ru.boristolstukha.moneytracker.repository.TransactionRepository;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    final private TransactionRepository transactionRepository;
    final private TransactionConverter transactionConverter;

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
    public TransactionDTO getTransaction(@PathVariable(name = "id") Long id) throws HttpNotFoundException{
        Transaction transaction = transactionRepository.findOne(id);
        if(transaction == null){
            throw new HttpNotFoundException();
        }
        return transactionConverter.entityToDTO(transaction);
    }


    @RequestMapping( path = "", method = {RequestMethod.POST, RequestMethod.PUT})
    public void putTransaction(@RequestBody TransactionDTO transactionDTO){
        Transaction transaction = transactionConverter.DTOToEntity(transactionDTO);

        transactionRepository.save(transaction);
    }
}
