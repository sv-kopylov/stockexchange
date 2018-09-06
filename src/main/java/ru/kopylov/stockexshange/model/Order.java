package ru.kopylov.stockexshange.model;

/**
 * Order
 */
public class Order {
    private final Customer customer;
    private final Share share;
    private final Type type;
    private final long pricePerShare;
    private final long sharesNum;

    public Order(Customer customer, Share share, Type type, long pricePerShare, long sharesNum) {
        this.customer = customer;
        this.share = share;
        this.type = type;
        this.pricePerShare = pricePerShare;
        this.sharesNum = sharesNum;
    }

    public enum Type{
        SALE, BUY
    }

    public Share getShare() {
        return share;
    }

    public Type getType() {
        return type;
    }

    public long getPricePerShare() {
        return pricePerShare;
    }

    public long getSharesNum() {
        return sharesNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (pricePerShare != order.pricePerShare) return false;
        if (sharesNum != order.sharesNum) return false;
        if (customer != null ? !customer.equals(order.customer) : order.customer != null) return false;
        if (share != null ? !share.equals(order.share) : order.share != null) return false;
        return type == order.type;
    }

}
