package ru.kopylov.stockexshange.bl;

import ru.kopylov.stockexshange.model.Order;

/**
 * Created by se on 14.09.2018.
 */
public class SuperMemoryFriendlyOrderPool implements OrderPool {
    @Override
    public Order pollMatching(Order order) {
        return null;
    }

    @Override
    public void add(Order order) {

    }

    @Override
    public long size() {
        return 0;
    }

    @Override
    public String toString() {
        return super.toString();
    }
private final int TRESHOLD = 1023;
    int getIndex(Order order){
        int index = 0;
        int share = order.getShare().getIndex();
        int price = (int)order.getPricePerShare();
        int num =(int) order.getSharesNum();
        if(share>TRESHOLD||price>TRESHOLD||num>TRESHOLD){
            return -1;
        }
        sb.append(order.getShare().getIndex());
        sb.append(order.getPricePerShare());
        sb.append(order.getSharesNum());
        return 0;
    }
}
