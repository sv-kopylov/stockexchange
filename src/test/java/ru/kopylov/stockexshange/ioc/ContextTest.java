package ru.kopylov.stockexshange.ioc;

import org.junit.Test;
import ru.kopylov.stockexshange.DAO.Register;
import ru.kopylov.stockexshange.DAO.RegisterImpl;

import static org.junit.Assert.*;

/**
 * Created by se on 05.09.2018.
 */
public class ContextTest {
    @Test
    public void getInstance() throws Exception {
        assertNotNull(Context.getInstance());
    }

    @Test
    public void lookup(){
        Register reg = new RegisterImpl();
        Context.getInstance().add(reg);
        assertNotNull( Context.getInstance().lookup(RegisterImpl.class));

    }

}