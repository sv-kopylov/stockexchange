package ru.kopylov.stockexshange.bl;

import org.junit.Before;
import org.junit.Test;
import ru.kopylov.stockexshange.DAO.CustomerDAO;
import ru.kopylov.stockexshange.DAO.Register;
import ru.kopylov.stockexshange.DAO.RegisterImpl;
import ru.kopylov.stockexshange.DAO.ShareDAO;
import ru.kopylov.stockexshange.ioc.Context;
import ru.kopylov.stockexshange.load.ApplicationDataLoader;
import ru.kopylov.stockexshange.model.Customer;
import ru.kopylov.stockexshange.model.Order;
import ru.kopylov.stockexshange.model.RegisterItem;
import ru.kopylov.stockexshange.model.Share;

import static org.junit.Assert.*;

/**
 * Created by se on 06.09.2018.
 */
public class OrderExecutorImplTest {
    private String clientFileName = "src\\test\\clients.txt";
    private String ordersFileName = "src\\test\\orders.txt";
    CustomerDAO customerDAO;
    ShareDAO shareDAO;
    Register register;
    Context ctx;

    @Before
    public void setUp() throws Exception{
        ApplicationDataLoader applicationDataLoader = new ApplicationDataLoader(clientFileName, ordersFileName);
        applicationDataLoader.load();
        ctx = Context.getInstance();
        customerDAO = (CustomerDAO)ctx.lookup(CustomerDAO.class);
        shareDAO = (ShareDAO)ctx.lookup(ShareDAO.class);
        register = (RegisterImpl)ctx.lookup(RegisterImpl.class);
    }


    @Test
    public void execute() throws Exception {
        long price = 11;
        long num =2;
        long totalBalance = customerDAO.getTotalBalance();

        OrderExecutor orderExecutor = new OrderExecutorImpl();
        orderExecutor.init();

        Customer c1 = customerDAO.get("C1");
        long c1balance = c1.getCashBalance();
        Customer c2 = customerDAO.get("C2");
        long c2balance = c2.getCashBalance();
        Share s1 = shareDAO.get(1);

        RegisterItem ri1 = register.find(c1, s1);
        long c1shares = ri1.getItemBalance();
        RegisterItem ri2 = register.find(c2, s1);
        long c2shares = ri2.getItemBalance();

        Order o1 = new Order(c1, s1, Order.Type.SALE, price, num );
        Order o2 = new Order(c2, s1, Order.Type.BUY, price, num );

        orderExecutor.execute(o1, o2);

        assertTrue(o1.isExecuted());
        assertTrue(o2.isExecuted());


        assertEquals(totalBalance, customerDAO.getTotalBalance());
        assertEquals(c1balance+(price*num),c1.getCashBalance());
        assertEquals(c2balance-(price*num),c2.getCashBalance());

        assertEquals(c1shares-num, ri1.getItemBalance());
        assertEquals(c2shares+num, ri2.getItemBalance());


    }

}