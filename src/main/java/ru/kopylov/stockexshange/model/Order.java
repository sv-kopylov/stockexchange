package ru.kopylov.stockexshange.model;

/**
 * Order
 */
public class Order {
    private Customer customer;
    private Share share;
    private Type type;
    private long pricePerShare;
    private long sharesNum;

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
}
