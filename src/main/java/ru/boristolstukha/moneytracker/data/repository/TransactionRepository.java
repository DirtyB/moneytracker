package ru.boristolstukha.moneytracker.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.boristolstukha.moneytracker.data.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

}
