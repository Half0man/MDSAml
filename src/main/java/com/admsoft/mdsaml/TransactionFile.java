package com.admsoft.mdsaml;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import java.util.Date;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }




    public float getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(float amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public String getTransactionDate() {
        return transactionTime;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionTime = transactionDate;
    }







    public String getTypeOfAction() {
        return typeOfAction;
    }

    public void setTypeOfAction(String typeOfAction) {
        this.typeOfAction = typeOfAction;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }
}
