package ru.boristolstukha.moneytracker.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

public class TransactionDTO {

    public Long id;

    @Size(max = 300)
    public String description;

    @NotNull
    @DecimalMax(value = "1.0E+8", inclusive = false)
    @DecimalMin(value = "-1.0E+8", inclusive = false)
    public BigDecimal amount;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    public Date date;

}
