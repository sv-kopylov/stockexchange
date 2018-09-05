package ru.kopylov.stockexshange.load;

import ru.kopylov.stockexshange.DAO.CustomerDAO;
import ru.kopylov.stockexshange.DAO.RegisterImpl;
import ru.kopylov.stockexshange.DAO.ShareDAO;
import ru.kopylov.stockexshange.ioc.Context;
import ru.kopylov.stockexshange.model.Customer;
import ru.kopylov.stockexshange.model.RegisterItem;

/**
 *
 */
public class DataLoader {

    private String clientFileName;
    private String ordersFileName;
    private Context ctx = Context.getInstance();

    public DataLoader(String clientFileName, String ordersFileName) {
        this.clientFileName = clientFileName;
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
