package ru.kopylov.stockexshange.model;

/**
 * Клиент
 */
public class Customer {
    private String name;
    private long cashBalance;

    public Customer() {
    }

    public Customer(String name, long cashBalance) {
        this.name = name;
        this.cashBalance = cashBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(long cashBalance) {
        this.cashBalance = cashBalance;
    }


}
