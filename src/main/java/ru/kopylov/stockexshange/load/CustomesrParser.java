package ru.kopylov.stockexshange.load;

import org.apache.log4j.Logger;
import ru.kopylov.stockexshange.DAO.CustomerDAO;
import ru.kopylov.stockexshange.DAO.RegisterImpl;
import ru.kopylov.stockexshange.DAO.ShareDAO;
import ru.kopylov.stockexshange.Exceptions.CriticalException;
import ru.kopylov.stockexshange.Exceptions.NoSuchDefinitionException;
import ru.kopylov.stockexshange.Exceptions.NotCriticalException;
import ru.kopylov.stockexshange.ioc.Context;
import ru.kopylov.stockexshange.model.Customer;
import ru.kopylov.stockexshange.model.RegisterItem;
import ru.kopylov.stockexshange.model.Share;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by se on 04.09.2018.
 */
public class CustomesrParser implements Parser {
    CustomerDAO customerDAO;
    RegisterImpl register;
    ShareDAO shareDAO;
    private static Logger logger = Logger.getLogger(CustomesrParser.class);

    @Override
    public void init() {
        try {
            customerDAO = (CustomerDAO) Context.getInstance().lookup(CustomerDAO.class);
            register = (RegisterImpl) Context.getInstance().lookup(RegisterImpl.class);
            shareDAO = (ShareDAO) Context.getInstance().lookup(ShareDAO.class);
        } catch (NoSuchDefinitionException e) {
            throw new CriticalException(e.getMessage());
        }

    }
    @Override
    public void parse(String path) {
        try {
            Files.lines(Paths.get(path)).forEach(s->parceOneCustomer(s));
        } catch (IOException e) {
            logger.error("Application fall doun because of problem with file: "+path);
            throw new CriticalException(e.getMessage());
        }
    }

    private void parceOneCustomer(String line) {
        String[] arr = line.split(COLUMN_DELIMITER);
        if(arr.length<2){
            logger.error("Parsing error, incorrect line in file Customers: "+line +
                    "customer will not be added to table");
            return;
        }
        try{
            Customer customer = makeCustomer(arr[0],arr[1]);
            customerDAO.add(customer);

            RegisterItem ri;
            for (int i = 2; i < arr.length; i++) {
                ri=makeRegisterItem(customer, i, arr[i]);
                register.add(ri);
            }
        } catch (NotCriticalException e){
            logger.error(e.getMessage());
        }


    }
    private Customer makeCustomer(String name, String sum) throws NotCriticalException {
        long cashBalance;
        try{
            cashBalance=Long.parseLong(sum);
        } catch (NumberFormatException e){
            throw new NotCriticalException("Customer field contains incorrect cash sum: "+e.getMessage()+"" +
                    "customer "+name+"will no be addet to table");
        }
        return new Customer(name, cashBalance);
    }
    private RegisterItem makeRegisterItem(Customer customer, int colIndex, String num) throws NotCriticalException {
        colIndex-=1;
        Share share = shareDAO.get(colIndex);
        long itemBalance = -1;
        if(share==null){
            throw new NotCriticalException("No share found under index: "+colIndex+
                    "Item will not be added to regisrter");
        }
        try{
            itemBalance = Long.parseLong(num);
        }catch (NumberFormatException e){
            throw new NotCriticalException("Customer field contains incorrect cash share num: "+e.getMessage()+
                    "Item will not be added to regisrter");
        }
        return new RegisterItem(customer, share, itemBalance);
    }

}
