package ru.kopylov.stockexshange.load;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.kopylov.stockexshange.DAO.CustomerDAO;
import ru.kopylov.stockexshange.DAO.OrderDAO;
import ru.kopylov.stockexshange.DAO.RegisterImpl;
import ru.kopylov.stockexshange.DAO.ShareDAO;
import ru.kopylov.stockexshange.Exceptions.NoSuchDefinitionException;
import ru.kopylov.stockexshange.ioc.Context;

import static org.junit.Assert.*;

import java.nio.file.Paths;

/**
 * Created by se on 05.09.2018.
 */
public class ParserTest {
    private static Logger logger = Logger.getLogger(ParserTest.class);
    private String clientFileName = "src\\test\\clients.txt";
    private String ordersFileName = "src\\test\\orders.txt";
    private Context ctx = Context.getInstance();
    @Before
    public void setUp() throws Exception {
        ctx.add(new ShareDAO());
        ctx.add(new RegisterImpl());
        ctx.add(new CustomerDAO());
        ctx.add(new OrderDAO());

    }

    @Test
    public void init() throws Exception {

    }

    @Test
    public void parceClients() throws Exception {
        Parser parser = new CustomesrParser();
        parser.init();
        parser.parse(clientFileName);
        assertEquals(9,((CustomerDAO)ctx.lookup(CustomerDAO.class)).count());
        assertEquals(36, ((RegisterImpl)ctx.lookup(RegisterImpl.class)).count());
    }

    @Test
    public void parceOrders() throws Exception {
        Parser parser = new CustomesrParser();
        parser.init();
        parser.parse(clientFileName);
        parser = new OrdersParser();
        parser.init();
        parser.parse(ordersFileName);
        assertEquals(8070, ((OrderDAO)ctx.lookup(OrderDAO.class)).count());

    }

}