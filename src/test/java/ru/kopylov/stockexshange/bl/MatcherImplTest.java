package ru.kopylov.stockexshange.bl;

import org.apache.log4j.Logger;
import org.junit.Test;
import ru.kopylov.stockexshange.load.ApplicationDataLoader;

import static org.junit.Assert.*;

/**
 * Created by se on 07.09.2018.
 */
public class MatcherImplTest {
    private static Logger logger = Logger.getLogger(OrderPoolImplTest.class);
    private String clientFileName = "src\\test\\clients.txt";
    private String ordersFileName = "src\\test\\orders.txt";
    @Test
    public void processOrders() throws Exception {
        ApplicationDataLoader applicationDataLoader = new ApplicationDataLoader(clientFileName, ordersFileName);
        applicationDataLoader.load();

        

        Matcher matcher = new MatcherImpl();
        matcher.init();
        matcher.processOrders();
    }

}