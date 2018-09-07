package ru.kopylov.stockexshange.bl;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.kopylov.stockexshange.DAO.OrderDAO;
import ru.kopylov.stockexshange.ioc.Context;
import ru.kopylov.stockexshange.load.ApplicationDataLoader;
import ru.kopylov.stockexshange.model.Customer;
import ru.kopylov.stockexshange.model.Order;
import ru.kopylov.stockexshange.model.Share;

import static org.junit.Assert.*;

/**
 * Created by se on 06.09.2018.
 */
public class OrderPoolImplTest {
    private static Logger logger = Logger.getLogger(OrderPoolImplTest.class);
    private String clientFileName = "src\\test\\clients.txt";
    private String ordersFileName = "src\\test\\orders.txt";
    private OrderDAO orderDAO;

    private Share s1 = new Share("A",1);
    private Share s2 = new Share("B", 1);
    private Customer c1 = new Customer("C1", 1000);



    @Test
    public void add() throws Exception {
        ApplicationDataLoader applicationDataLoader = new ApplicationDataLoader(clientFileName, ordersFileName);
        applicationDataLoader.load();
        orderDAO = (OrderDAO) Context.getInstance().lookup(OrderDAO.class);
        OrderPool orderPool = new OrderPoolImpl();
        Order order;
        long initialSize = orderDAO.count();
        long cnt = 0;
        long start = System.currentTimeMillis();
        while ((order = orderDAO.poll()) != null) {
            orderPool.add(order);
            cnt++;
        }

        logger.debug("added: " + cnt + " orders");
        logger.debug("adding time, mils: " + (System.currentTimeMillis() - start));
        assertEquals(initialSize, cnt);

    }

    @Test
    public void pollMatchingIfPresent() throws Exception {
        OrderPool orderPool = new OrderPoolImpl();

        Order o1 = new Order(c1, s1, Order.Type.BUY, 21, 2);
        Order o2 = new Order(c1, s1, Order.Type.SALE, 21, 2);


        orderPool.add(o1);
        Order actual = orderPool.pollMatching(o2);
        assertEquals(o1,actual);
        actual = orderPool.pollMatching(o2);
        assertNull(actual);
    }
    @Test
    public void pollMatchingIfAbsent() throws Exception {
        OrderPool orderPool = new OrderPoolImpl();

        Order o1 = new Order(c1, s1, Order.Type.BUY, 21, 2);
        Order o2 = new Order(c1, s2, Order.Type.SALE, 21, 2);


        Order actual = orderPool.pollMatching(o2);
        assertNull(actual);
    }

}