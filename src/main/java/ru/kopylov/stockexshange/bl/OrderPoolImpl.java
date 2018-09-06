package ru.kopylov.stockexshange.bl;

import ru.kopylov.stockexshange.model.Order;
import ru.kopylov.stockexshange.model.Share;

import java.util.*;

/**
 * Created by se on 06.09.2018.
 */
public class OrderPoolImpl implements OrderPool{
    private final Map<Share, Map<Long, Map<Long, LinkedList<Order>>>> pool = new HashMap<>();

    @Override
    public void add(Order order) {
        Map<Long, Map<Long, LinkedList<Order>>> priceMap = pool.get(order.getShare());
        Map<Long, LinkedList<Order>> numMap;
        LinkedList<Order> sameOrdersList;

        if(priceMap==null){
            priceMap=new TreeMap<>();
            numMap = new TreeMap<>();
            sameOrdersList = new LinkedList<>();

            add(order, sameOrdersList, numMap, priceMap);
            pool.put(order.getShare(), priceMap);
        } else if((numMap=priceMap.get(order.getPricePerShare()))==null){
            numMap = new TreeMap<>();
            sameOrdersList = new LinkedList<>();

            add(order, sameOrdersList, numMap, priceMap);
        } else if((sameOrdersList=numMap.get(order.getSharesNum()))==null){
            sameOrdersList = new LinkedList<>();

            add(order, sameOrdersList, numMap, priceMap);
        } else {

            add(order, sameOrdersList, numMap, priceMap);
        }
    }

    private void add(Order order,
                     LinkedList<Order> sameOrdersList,
                     Map<Long, LinkedList<Order>> numMap,
                     Map<Long, Map<Long, LinkedList<Order>>> priceMap) {
        sameOrdersList.offer(order);
        numMap.put(order.getSharesNum(), sameOrdersList);
        priceMap.put(order.getPricePerShare(), numMap);
    }



    @Override
    public Order mathc(Order order) {
        return null;
    }


}
