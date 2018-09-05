package ru.kopylov.stockexshange.load;

import ru.kopylov.stockexshange.model.Order;

import java.util.Queue;

/**
 * Created by se on 04.09.2018.
 */
public interface OrdersParcer {
    Queue<Order> parce(String filename);

}
