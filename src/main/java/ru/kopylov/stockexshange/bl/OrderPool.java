package ru.kopylov.stockexshange.bl;

import ru.kopylov.stockexshange.model.Order;

/**
 * Created by se on 05.09.2018.
 */
public interface OrderPool {
    Order pollMatching(Order order);
    void add(Order order);
    long size();
}
