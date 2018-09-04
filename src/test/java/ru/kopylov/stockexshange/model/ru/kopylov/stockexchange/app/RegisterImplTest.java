package ru.kopylov.stockexshange.model.ru.kopylov.stockexchange.app;

import org.junit.Before;
import org.junit.Test;
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

    @Before
    public void init() {


        register = new RegisterImpl();



    }
    @Test
    public void findItem() throws Exception {

    }

}