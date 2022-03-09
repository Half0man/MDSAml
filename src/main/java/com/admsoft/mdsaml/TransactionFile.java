package com.admsoft.mdsaml;

import com.opencsv.bean.CsvBindByName;

import java.util.Date;

public class TransactionFile {
    @CsvBindByName
    private long id;
    @CsvBindByName
    private String typeOfAction;
    @CsvBindByName
    private int sourceId;
    @CsvBindByName
    private int destinationId;
    @CsvBindByName
    private float amountOfMoney;
    @CsvBindByName
    private Date transactionDate;
    @CsvBindByName
    private String title;
    public TransactionFile(long id,String typeOfAction,int sourceId,int destinationId,float amountOfMoney,Date transactionDate,String title){
        this.id=id;
        this.typeOfAction=typeOfAction;
        this.sourceId=sourceId;
        this.destinationId=destinationId;
        this.amountOfMoney=amountOfMoney;
        this.transactionDate=transactionDate;
        this.title=title;
    }
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

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
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
