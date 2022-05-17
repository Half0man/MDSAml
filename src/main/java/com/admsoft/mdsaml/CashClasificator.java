package com.admsoft.mdsaml;

public class CashClasificator {
    public String clasifyAmount(float amount){

        if (amount<=100){
            return "0-100";
        }
        if (amount<=500&&amount>100){
            return "100-500";
        }
        if (amount<=1000&&amount>500){
            return "500-1000";
        }
        if (amount<=10000&&amount>1000){
            return "1000-10000";
        }
        if (amount<=100000&&amount>10000){
            return "10000-100000";
        }
        if (amount<=1000000&&amount>100000){
            return "100000-1000000";
        }
        if (amount>1000000){
            return "1000000-";
        }
        else return "0-100";
    }
    public String clasifyAmountleft(float amount){

        if (amount<=100){
            return "0-100";
        }
        if (amount<=500&&amount>100){
            return "100-500";
        }
        if (amount<=1000&&amount>500){
            return "500-1000";
        }
        if (amount<=10000&&amount>1000){
            return "1000-10000";
        }
        if (amount<=100000&&amount>10000){
            return "10000-100000";
        }
        if (amount<=1000000&&amount>100000){
            return "100000-1000000";
        }
        if (amount<=10000000&&amount>1000000){
            return "1000000-10000000";
        }
        if (amount>10000000){
            return "10000000+";
        }
        else return "0-100";
    }
}
