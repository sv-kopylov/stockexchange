package ru.kopylov.stockexshange.load;

import org.apache.log4j.Logger;
import ru.kopylov.stockexshange.DAO.CustomerDAO;
import ru.kopylov.stockexshange.DAO.OrderDAO;
import ru.kopylov.stockexshange.DAO.ShareDAO;
import ru.kopylov.stockexshange.Exceptions.CriticalException;
import ru.kopylov.stockexshange.Exceptions.NoSuchDefinitionException;
import ru.kopylov.stockexshange.ioc.Context;
import ru.kopylov.stockexshange.model.Customer;
import ru.kopylov.stockexshange.model.Order;
import ru.kopylov.stockexshange.model.Share;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Queue;

/**
 * Created by se on 04.09.2018.
 */
public class OrdersParser implements Parser {
    OrderDAO orderDAO;
    CustomerDAO customerDAO;
    ShareDAO shareDAO;
    private static Logger logger = Logger.getLogger(OrdersParser.class);


    @Override
    public void init() {
        try {
            orderDAO = (OrderDAO) Context.getInstance().lookup(OrderDAO.class);
            customerDAO = (CustomerDAO) Context.getInstance().lookup(CustomerDAO.class);
            shareDAO = (ShareDAO) Context.getInstance().lookup(ShareDAO.class);
        } catch (NoSuchDefinitionException e) {
            throw new CriticalException(e.getMessage());
        }

    }

    @Override
    public void parse(String path) {
        try {
            Files.lines(Paths.get(path)).forEach(s->parceOneOrder(s));
        } catch (IOException e) {
            logger.error("Application fall doun because of problem with file: "+path);
            throw new CriticalException(e.getMessage());
        }

    }

    private void parceOneOrder(String line) {
        String[] arr = line.split(COLUMN_DELIMITER);
        if (arr.length != ORDERS_TABLE_COLUMNS) {
            logger.error("Parsing error, incorrect line in file Orders: " + line +
                    "order will not be added to queue");
            return;
        }

        Customer customer = customerDAO.get(arr[0]);
        Share share = shareDAO.get(arr[2]);
        if (customer == null || share == null) {
            logger.error("Parsing error, customer or share not found, order will not be added to queue");
            return;
        }

        Order.Type type;
        if(arr[1].equalsIgnoreCase(ORDERS_SALE_SYMBOL)){
            type= Order.Type.SALE;
        } else if(arr[1].equalsIgnoreCase(ORDERS_BUY_SYMBOL)){
            type= Order.Type.BUY;
        } else{
            logger.error("Parsing error, order type not determined: "+arr[1]);
            return;
        }

        long pricePerShare = -1;
        long sharesNum = -1;
        try{
            pricePerShare = Long.parseLong(arr[3]);
            sharesNum = Long.parseLong(arr[4]);
        } catch (NumberFormatException e){
            logger.error("Parsing error, order field contains incorrect sums "+arr[3]+", "+arr[4]);
            return;
        }
            orderDAO.add(new Order(customer, share, type, pricePerShare, sharesNum));


    }

}
