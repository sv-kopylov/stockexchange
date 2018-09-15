package ru.kopylov.stockexshange.bl;

import ru.kopylov.stockexshange.model.Order;

import java.util.*;

/**
 * Created by se on 13.09.2018.
 */
public class MemoryFriendlyOrderPool implements OrderPool {
    Map<String, LinkedList<Order>> map = new TreeMap<>();
    long cnt=0;

    @Override
    public Order pollMatching(Order order) {
        String key = getIndex(order);
        LinkedList <Order> list = map.get(key);
        if(list==null){
            return null;
        } else {
            cnt--;
            Order result = list.pollFirst();
            if(list.size()==0){
                map.remove(key);
            }
            return result;
        }

    }

    @Override
    public void add(Order order) {
        cnt++;
        String key = getIndex(order);
        LinkedList <Order> list = map.get(key);
        if(list!=null){
            list.addLast(order);
        } else {
            list = new LinkedList<>();
            list.addLast(order);
            map.put(key, list);
        }

    }

    @Override
    public long size() {
        return cnt;
    }

    String getIndex(Order order){
        StringBuilder sb = new StringBuilder();
        sb.append(order.getShare().getIndex());
        sb.append(order.getPricePerShare());
        sb.append(order.getSharesNum());
        return sb.toString();
    }
}
