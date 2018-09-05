package ru.kopylov.stockexshange.DAO;

import ru.kopylov.stockexshange.model.Order;

import java.util.LinkedList;

/**
 * Created by se on 05.09.2018.
 */
public class OrderDAO {
    private final LinkedList<Order> list = new LinkedList<>();
    public void add(Order order){
        list.offer(order);
    }
    public Order poll(){
       return list.poll();
    }
    public long count(){
        return list.size();
    }

}
