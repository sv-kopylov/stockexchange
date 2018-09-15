package ru.kopylov.stockexshange.bl;

import ru.kopylov.stockexshange.Exceptions.CriticalException;
import ru.kopylov.stockexshange.model.Order;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by se on 14.09.2018.
 */
public class SuperMemoryFriendlyOrderPool implements OrderPool {
    Map <Long, LinkedList<Order>> map = new TreeMap<>();
    long cnt=0;
    @Override
    public Order pollMatching(Order order) {
        long key = getIndex(order);
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
        long key = getIndex(order);
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
        return 0;
    }

    @Override
    public String toString() {
        return super.toString();
    }
private final long TRESHOLD = 268435455;
    private final long SHARE_INDEX_TRESHOLD = 127;
    long getIndex(Order order){
        long index = 0;
        long share = order.getShare().getIndex();
        long price = order.getPricePerShare();
        long num = order.getSharesNum();
        if(share>SHARE_INDEX_TRESHOLD||price>TRESHOLD||num>TRESHOLD){
            throw new CriticalException("Ahtung!!!! Extremely rich user in system!!!!");
        }
        index|=(share<<56);
        index|=(price<<28);
        index|=num;


        return index;
    }
}
