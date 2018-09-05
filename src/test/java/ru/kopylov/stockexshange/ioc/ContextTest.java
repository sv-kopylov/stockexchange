package ru.kopylov.stockexshange.ioc;

import org.junit.Before;
import org.junit.Test;
import ru.kopylov.stockexshange.DAO.Register;
import ru.kopylov.stockexshange.DAO.RegisterImpl;
import ru.kopylov.stockexshange.Exceptions.NoSuchDefinitionException;

import static org.junit.Assert.*;

/**
 * Created by se on 05.09.2018.
 */
public class ContextTest {
    Register reg;
    @Before
    public void setUp(){
        reg = new RegisterImpl();
        Context.getInstance().add(reg);
    }

    @Test
    public void getInstance() throws Exception {
        assertNotNull(Context.getInstance());
    }

    @Test
    public void lookup() throws NoSuchDefinitionException {
        assertNotNull( Context.getInstance().lookup(RegisterImpl.class));
    }

    @Test(expected = NoSuchDefinitionException.class)
    public void lookupNegative() throws NoSuchDefinitionException {
        assertNotNull( Context.getInstance().lookup(Register.class));
    }

}