package ru.kopylov.stockexshange.load;

import org.apache.log4j.Logger;
import ru.kopylov.stockexshange.DAO.CustomerDAO;
import ru.kopylov.stockexshange.DAO.Register;
import ru.kopylov.stockexshange.DAO.RegisterImpl;
import ru.kopylov.stockexshange.Exceptions.CriticalException;
import ru.kopylov.stockexshange.Exceptions.NoSuchDefinitionException;
import ru.kopylov.stockexshange.ioc.Context;
import ru.kopylov.stockexshange.model.Customer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 */
public class Saver {
    private final String filename;
    private CustomerDAO customerDAO;
    private Register register;
    private static final Logger logger = Logger.getLogger(Saver.class);

    public Saver(String filename) {
        this.filename = filename;
    }

    public void init() {
        try {
            customerDAO = (CustomerDAO) Context.getInstance().lookup(CustomerDAO.class);
            register = (RegisterImpl) Context.getInstance().lookup(RegisterImpl.class);
        } catch (NoSuchDefinitionException e) {
            throw new CriticalException(e.getMessage());
        }

    }

    public void save(){
        try (BufferedWriter br = Files.newBufferedWriter(Paths.get(filename))) {
            customerDAO.selectAll()
                    .stream()
                    .sorted((c1, c2)->c1.getName().compareTo(c2.getName()))
                    .map(this::prepareString)
                    .forEach(s->writeString(s, br));
        } catch (IOException e) {
            logger.error("Application cant save file, because of problems with file : "+filename);
            throw new CriticalException(e.getMessage());
        }

    }

    private String prepareString(Customer customer){
        StringBuilder sb = new StringBuilder();
        sb.append(customer.getName());
        sb.append(Parser.COLUMN_DELIMITER);
        sb.append(customer.getCashBalance());
        sb.append(Parser.COLUMN_DELIMITER);

        register.find(customer).
                stream()
                .sorted((ri1, ri2)->ri1.getShare().getIndex()-ri2.getShare().getIndex())
                .forEach(ri->{
                    sb.append(ri.getItemBalance());
                    sb.append(Parser.COLUMN_DELIMITER);
                });

        return sb.substring(0, sb.length()-2);

    }

    private void writeString(String s, BufferedWriter bf){
        try {
            bf.write(s);
            bf.newLine();
        } catch (IOException e) {
            logger.error("Application cant save file, because of problems with file : "+filename);
            throw new CriticalException(e.getMessage());
        }
    }
}
