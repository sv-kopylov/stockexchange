package ru.kopylov.stockexshange.DAO;

import ru.kopylov.stockexshange.model.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by se on 05.09.2018.
 */
public class CustomerDAO {
    private final List<Customer> list = new ArrayList<>();

    public void add(Customer customer){
        if(customer!=null){
            list.add(customer);
        }
    }
    public Customer get(String name){
        for(Customer c:list){
            if(c.getName().equals(name)){
                return c;
            }
        }
        return null;
    }

    public long getTotalBalance(){
       return list.stream().mapToLong(Customer::getCachBalance).sum();
    }
    public long count(){
        return list.size();
    }


}
