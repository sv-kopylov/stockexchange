package ru.kopylov.stockexshange.load;

import ru.kopylov.stockexshange.DAO.CustomerDAO;
import ru.kopylov.stockexshange.DAO.RegisterImpl;
import ru.kopylov.stockexshange.DAO.ShareDAO;
import ru.kopylov.stockexshange.ioc.Context;

/**
 *
 */
public class DataLoader {

    private String clientsFileName;
    private String ordersFileName;
    private Context ctx = Context.getInstance();

    public DataLoader(String clientsFileName, String ordersFileName) {
        this.clientsFileName = clientsFileName;
        this.ordersFileName = ordersFileName;
    }

    public void load() {
        initContext();
    }


    private void initContext() {
        ctx.add(new ShareDAO());
        ctx.add(new RegisterImpl());
        ctx.add(new CustomerDAO());
    }


}
