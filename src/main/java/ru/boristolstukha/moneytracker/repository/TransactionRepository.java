package ru.boristolstukha.moneytracker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.boristolstukha.moneytracker.entity.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Long>{

}
