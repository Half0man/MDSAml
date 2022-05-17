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
    private long id;
    @Column(nullable = false, length = 60)
    @CsvBindByName
    private String typeOfAction;
    @Column(nullable = false, length = 60)
    @CsvBindByName
    private String sourceId;
    @Column(nullable = false, length = 60)
    @CsvBindByName
    private float amountOfMoney;
    @Column(nullable = false, length = 60)
    @CsvBindByName
    private String transactionTime;
    @Column(nullable = false, length = 60)
    @CsvBindByName
    private float amountOnMoneyLeft;

    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name="client_id")
    private Client client;
}
