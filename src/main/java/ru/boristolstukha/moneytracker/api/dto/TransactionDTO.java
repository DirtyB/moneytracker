package ru.boristolstukha.moneytracker.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TransactionDTO {

    public Long id;
    public String description;
    public Double amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    public Date date;

}
