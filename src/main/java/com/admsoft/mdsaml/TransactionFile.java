package com.admsoft.mdsaml;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "transactionFiles")
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
    private int destinationId;
    @Column(nullable = false, length = 60)
    @CsvBindByName
    private float amountOfMoney;
    @Column(nullable = false, length = 60)
    @CsvBindByName
    private String transactionDate;
    @Column(nullable = false, length = 60)
    @CsvBindByName
    private String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    public float getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(float amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
