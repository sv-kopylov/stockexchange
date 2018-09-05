package ru.kopylov.stockexshange.load;

import ru.kopylov.stockexshange.DAO.Register;
import ru.kopylov.stockexshange.DAO.RegisterImpl;
import ru.kopylov.stockexshange.ioc.Context;
import ru.kopylov.stockexshange.model.Customer;
import ru.kopylov.stockexshange.model.Share;
import ru.kopylov.stockexshange.settings.Settings;

import java.util.List;

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


    private List<Share> sharesList = Settings.getInstance().getShares();
    private List<Customer> customerList ;
    private Register register = new RegisterImpl();



    private CustomesrParser customesrParser;
    private OrdersParcer ordersParcer;


}
