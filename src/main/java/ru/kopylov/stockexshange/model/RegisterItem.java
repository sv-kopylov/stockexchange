package ru.kopylov.stockexshange.model;

/**
 * Implementation many to many relationship
 */
public class RegisterItem {
    private Customer customer;
    private Share share;
//    it could be Long
    private long itemBalance=-1;

    public RegisterItem(Customer customer, Share share) {
        this.customer = customer;
        this.share = share;
    }

    public RegisterItem(Customer customer, Share share, long itemBalance) {
        this.customer = customer;
        this.share = share;
        this.itemBalance = itemBalance;
    }

    public RegisterItem() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Share getShare() {
        return share;
    }

    public void setShare(Share share) {
        this.share = share;
    }

    public long getItemBalance() {
        return itemBalance;
    }

    public void setItemBalance(long itemBalance) {
        this.itemBalance = itemBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegisterItem)) return false;

        RegisterItem that = (RegisterItem) o;

        if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;
        return share != null ? share.equals(that.share) : that.share == null;
    }

    @Override
    public int hashCode() {
        int result = customer != null ? customer.hashCode() : 0;
        result = 31 * result + (share != null ? share.hashCode() : 0);
        return result;
    }
}
