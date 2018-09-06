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
}
