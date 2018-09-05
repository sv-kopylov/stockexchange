package ru.kopylov.stockexshange.load;

import ru.kopylov.stockexshange.DAO.CustomerDAO;
import ru.kopylov.stockexshange.DAO.OrderDAO;
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
        fillTables();
    }

    private void initContext() {
        ctx.add(new ShareDAO());
        ctx.add(new RegisterImpl());
        ctx.add(new CustomerDAO());
        ctx.add(new OrderDAO());
    }

    private void fillTables(){
        Parser parser = new CustomesrParser();
        parser.init();
        parser.parse(clientsFileName);
        parser = new OrdersParser();
        parser.init();
        parser.parse(ordersFileName);
    }


}
