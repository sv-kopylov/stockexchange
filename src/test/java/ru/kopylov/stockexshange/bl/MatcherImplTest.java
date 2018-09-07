package ru.kopylov.stockexshange.bl;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.kopylov.stockexshange.DAO.*;
import ru.kopylov.stockexshange.ioc.Context;
import ru.kopylov.stockexshange.load.ApplicationDataLoader;
import ru.kopylov.stockexshange.load.Saver;

import static org.junit.Assert.*;

/**
 * Created by se on 07.09.2018.
 */
public class MatcherImplTest {
    private static Logger logger = Logger.getLogger(OrderPoolImplTest.class);
    private String clientFileName = "src\\test\\clients.txt";
    private String ordersFileName = "src\\test\\orders.txt";
    private String resultFileName = "src\\test\\result.txt";
    private OrderDAO orderDAO;
    private Register register;
    private CustomerDAO customerDAO;
    private ShareDAO shareDAO;

    @Before
    public void setUp() throws Exception{
        ApplicationDataLoader applicationDataLoader = new ApplicationDataLoader(clientFileName, ordersFileName);
        applicationDataLoader.load();
        Context ctx = Context.getInstance();
        customerDAO = (CustomerDAO)ctx.lookup(CustomerDAO.class);
        shareDAO = (ShareDAO)ctx.lookup(ShareDAO.class);
        register = (RegisterImpl)ctx.lookup(RegisterImpl.class);
        orderDAO = (OrderDAO)ctx.lookup(OrderDAO.class);
    }

    @Test
    public void processOrders() throws Exception {


        Matcher matcher = new MatcherImpl();
        matcher.init();

        long totalBalance = customerDAO.getTotalBalance();
        long aBalance = register.getShareTotalBalance(shareDAO.get(1));
        long bBalance = register.getShareTotalBalance(shareDAO.get(2));
        long cBalance = register.getShareTotalBalance(shareDAO.get(3));
        long dBalance = register.getShareTotalBalance(shareDAO.get(4));

        logger.debug("Orders pool size before: "+orderDAO.count());

        long startTime = System.currentTimeMillis();

        matcher.processOrders();

        logger.debug("Execution time: "+(System.currentTimeMillis()-startTime));
        logger.debug("Orders pool size after: "+orderDAO.count());
        logger.debug("Not matching orders: buy: "+((MatcherImpl)matcher).getBuyPollSize()+" sale: "+((MatcherImpl)matcher).getSalePoolSize() );
        assertEquals(totalBalance, customerDAO.getTotalBalance());
        assertEquals(aBalance, register.getShareTotalBalance(shareDAO.get(1)));
        assertEquals(bBalance, register.getShareTotalBalance(shareDAO.get(2)));
        assertEquals(cBalance, register.getShareTotalBalance(shareDAO.get(3)));
        assertEquals(dBalance, register.getShareTotalBalance(shareDAO.get(4)));

        Saver saver = new Saver(resultFileName);
        saver.init();
        saver.save();

    }

}