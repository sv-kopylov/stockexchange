package ru.kopylov.stockexshange.bl;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.kopylov.stockexshange.DAO.OrderDAO;
import ru.kopylov.stockexshange.DAO.Register;
import ru.kopylov.stockexshange.ioc.Context;
import ru.kopylov.stockexshange.load.DataLoader;
import ru.kopylov.stockexshange.model.Order;

import static org.junit.Assert.*;

/**
 * Created by se on 06.09.2018.
 */
public class OrderPoolImplTest {
    private static Logger logger = Logger.getLogger(OrderPoolImplTest.class);
    private String clientFileName = "src\\test\\clients.txt";
    private String ordersFileName = "src\\test\\orders.txt";
    private OrderDAO orderDAO;
    @Before
    public void setUp() throws Exception {
        DataLoader dataLoader = new DataLoader(clientFileName, ordersFileName);
        dataLoader.load();
        orderDAO = (OrderDAO) Context.getInstance().lookup(OrderDAO.class);
    }

    @Test
    public void add() throws Exception {
        OrderPool orderPool = new OrderPoolImpl();
        Order order;
        long intialSize = orderDAO.count();
        long cnt=0;
        long start = System.currentTimeMillis();
        while ((order=orderDAO.poll())!=null){
            orderPool.add(order);
            cnt++;
        }

        logger.debug("added: "+ cnt+" orders");
        logger.debug("adding time, mils: "+(System.currentTimeMillis()-start));
        assertEquals(intialSize, cnt);

    }

}