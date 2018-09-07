package ru.kopylov.stockexshange.DAO;

import org.junit.Before;
import org.junit.Test;
import ru.kopylov.stockexshange.DAO.Register;
import ru.kopylov.stockexshange.DAO.RegisterImpl;
import ru.kopylov.stockexshange.model.Customer;
import ru.kopylov.stockexshange.model.RegisterItem;
import ru.kopylov.stockexshange.model.Share;

import static org.junit.Assert.*;

/**
 * Created by se on 04.09.2018.
 */
public class RegisterImplTest {
    Register register;
    Customer c1;
    Customer c2;
    Share s1;
    Share s2;
    RegisterItem ri11;
    RegisterItem ri12;
    RegisterItem ri21;
    RegisterItem ri22;
    long s1balance;
    long s2balance;

    @Before
    public void setUp() {
        c1 = new Customer("C1", 100);
        c2 = new Customer("C2", 200);

        s1=new Share("S1");
        s2=new Share("S2");

        ri11 = new RegisterItem(c1, s1);
        ri12 = new RegisterItem(c1, s2);
        ri21 = new RegisterItem(c2, s1);
        ri22 = new RegisterItem(c2, s2);
        s1balance = 40;
        s2balance = 60;

        ri11.setItemBalance(10);
        ri12.setItemBalance(20);
        ri21.setItemBalance(30);
        ri22.setItemBalance(40);




        register = new RegisterImpl();

        register.add(ri11);
        register.add(ri12);
        register.add(ri21);
        register.add(ri22);

    }
    @Test
    public void findItem() throws Exception {
        RegisterItem actual = register.find(c1, s2);
        RegisterItem expected = ri12;
        assertEquals(actual, expected);
        assertTrue(actual.getItemBalance()==expected.getItemBalance());
    }

    @Test
    public void getShareTotalBalance() throws Exception{
        assertEquals(s1balance, register.getShareTotalBalance(s1));
        assertEquals(s2balance, register.getShareTotalBalance(s2));

    }

}