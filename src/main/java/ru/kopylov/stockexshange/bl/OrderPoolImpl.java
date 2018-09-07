package ru.kopylov.stockexshange.bl;

import ru.kopylov.stockexshange.model.Order;
import ru.kopylov.stockexshange.model.Share;

import java.util.*;

/**
 * Created by se on 06.09.2018.
 */
public class OrderPoolImpl implements OrderPool {
    private final Map<Share, Map<Long, Map<Long, LinkedList<Order>>>> pool = new HashMap<>();
    private long cnt;

    @Override
    public void add(Order order) {
        Map<Long, Map<Long, LinkedList<Order>>> priceMap = pool.get(order.getShare());
        Map<Long, LinkedList<Order>> numMap;
        LinkedList<Order> sameOrdersList;

        if (priceMap == null) {
            priceMap = new TreeMap<>();
            numMap = new TreeMap<>();
            sameOrdersList = new LinkedList<>();

            sameOrdersList.addLast(order);
            numMap.put(order.getSharesNum(), sameOrdersList);
            priceMap.put(order.getPricePerShare(), numMap);
            pool.put(order.getShare(), priceMap);
        } else if ((numMap = priceMap.get(order.getPricePerShare())) == null) {
            numMap = new TreeMap<>();
            sameOrdersList = new LinkedList<>();

            sameOrdersList.addLast(order);
            numMap.put(order.getSharesNum(), sameOrdersList);
            priceMap.put(order.getPricePerShare(), numMap);
        } else if ((sameOrdersList = numMap.get(order.getSharesNum())) == null) {
            sameOrdersList = new LinkedList<>();

            sameOrdersList.addLast(order);
            numMap.put(order.getSharesNum(), sameOrdersList);
        } else {
            sameOrdersList.addLast(order);
        }
        cnt++;
    }

    @Override
    public Order pollMatching(Order order) {
        Order matchingOrder;
        Map<Long, Map<Long, LinkedList<Order>>> priceMap = pool.get(order.getShare());
        Map<Long, LinkedList<Order>> numMap;
        LinkedList<Order> sameOrdersList;
        if (priceMap == null) {
            return null;
        } else if ((numMap = priceMap.get(order.getPricePerShare())) == null) {
            return null;
        } else if ((sameOrdersList = numMap.get(order.getSharesNum())) == null) {
            return null;
        } else if ((matchingOrder = sameOrdersList.pollFirst()) == null) {
            return null;
        }
        cleanEmpty(order, sameOrdersList, numMap, priceMap);
        cnt--;
        return matchingOrder;
    }

    private void cleanEmpty(Order order,
                            LinkedList<Order> sameOrdersList,
                            Map<Long, LinkedList<Order>> numMap,
                            Map<Long, Map<Long, LinkedList<Order>>> priceMap) {
        if (sameOrdersList.size() == 0) {
            numMap.remove(order.getSharesNum());
        }
        if (numMap.size() == 0) {
            priceMap.remove(order.getPricePerShare());
        }
        if (priceMap.size() == 0) {
            pool.remove(order.getShare());
        }
    }

    @Override
    public long size() {
        return cnt;
    }
}
