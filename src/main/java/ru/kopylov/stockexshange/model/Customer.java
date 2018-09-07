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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;

        Customer customer = (Customer) o;

        if (cashBalance != customer.cashBalance) return false;
        return name != null ? name.equals(customer.name) : customer.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        return result;
    }
}
