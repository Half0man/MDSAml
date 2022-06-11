package com.admsoft.mdsaml;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "transaction_Files")
public class TransactionFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 60)
    @CsvBindByName
    private String typeOfAction;
    @CsvBindByName
    private float amountOfMoney;
    @Column(nullable = false, length = 60)
    @CsvBindByName
    private String transactionTime;
    @Column(nullable = false, length = 60)
    @CsvBindByName
    private float amountOnMoneyLeft;
    @Column(nullable = false, length = 60)
    @CsvBindByName
    private String isFraud;
    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name="client_id")
    private Client client;
}
