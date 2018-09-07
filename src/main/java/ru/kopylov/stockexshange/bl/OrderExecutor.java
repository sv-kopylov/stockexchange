package ru.kopylov.stockexshange.bl;

import ru.kopylov.stockexshange.model.Order;

/**
 * Created by se on 06.09.2018.
 */
public interface OrderExecutor {
    void init();
    void execute(Order o1, Order o2);
}
