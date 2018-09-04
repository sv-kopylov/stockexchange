package ru.kopylov.stockexshange.model;

/**
 * Клиент
 */
public class Customer {
    private String name;
    private long cachBalance;

    public Customer() {
    }

    public Customer(String name, long cachBalance) {
        this.name = name;
        this.cachBalance = cachBalance;
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


}
