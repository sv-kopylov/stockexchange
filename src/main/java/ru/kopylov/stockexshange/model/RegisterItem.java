package ru.kopylov.stockexshange.model;

/**
 * Implementation mamy to many relationship
 */
public class RegisterItem {
    private Customer customer;
    private Share share;
//    it also could be Long obj
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

        if (!customer.equals(that.customer)) return false;
        return share.equals(that.share);
    }

    @Override
    public int hashCode() {
        int result = customer.hashCode();
        result = 31 * result + share.hashCode();
        return result;
    }
}
