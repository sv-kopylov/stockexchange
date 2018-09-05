package ru.kopylov.stockexshange.load;

import org.junit.Before;
import org.junit.Test;
import ru.kopylov.stockexshange.DAO.CustomerDAO;
import ru.kopylov.stockexshange.DAO.RegisterImpl;
import ru.kopylov.stockexshange.DAO.ShareDAO;
import ru.kopylov.stockexshange.ioc.Context;

import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Created by se on 05.09.2018.
 */
public class CustomesrParserTest {
    private String clientFileName = "src\\test\\clients.txt";
    private String ordersFileName;
    private Context ctx = Context.getInstance();
    @Before
    public void setUp() throws Exception {
        ctx.add(new ShareDAO());
        ctx.add(new RegisterImpl());
        ctx.add(new CustomerDAO());

    }

    @Test
    public void init() throws Exception {
        System.out.println(Paths.get("src\\test").toAbsolutePath());
    }

    @Test
    public void parce() throws Exception {
        Parcer parcer = new CustomesrParser();
        parcer.init();
        parcer.parce(clientFileName);
        System.out.println();
    }

}