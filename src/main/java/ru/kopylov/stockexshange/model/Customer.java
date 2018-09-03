package ru.kopylov.stockexshange.model;

/**
 * Клиент
 */
public class Customer {
    private String name;
    private long cachBalance;
    private long[] sharesBalance;
    
    public Customer(){
        sharesBalance = new long[Shares.getInstance().getSize()];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCachBalance() {
        return cachBalance;
    }

    public void setCachBalance(long cachBalance) {
        this.cachBalance = cachBalance;
    }

    public long[] getSharesBalance() {
        return sharesBalance;
    }

    public void setSharesBalance(long[] sharesBalance) {
        this.sharesBalance = sharesBalance;
    }
}
