package com.admsoft.mdsaml;

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
    private int sourceId;
    @Column(nullable = false, length = 60)
    @CsvBindByName
    private float amountOfMoney;
    @Column(nullable = false, length = 60)
    @CsvBindByName
    private String transactionTime;
    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name="clients_id")
    private Client client;
}
