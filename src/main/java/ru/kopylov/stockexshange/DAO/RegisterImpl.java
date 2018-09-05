package ru.kopylov.stockexshange.DAO;

import ru.kopylov.stockexshange.model.Customer;
import ru.kopylov.stockexshange.model.RegisterItem;
import ru.kopylov.stockexshange.model.Share;

import java.util.*;

/**
 * Optimized by performance
 *
 */
// TODO make more resource-friendly impl
public class  RegisterImpl implements Register {

//    keys could be smaller
   private final Map<RegisterItem, RegisterItem> map = new HashMap<>();

    @Override
    public void addItem(RegisterItem registerItem) {
        map.put(registerItem, registerItem);

    }

    @Override
    public RegisterItem findItem(Customer customer, Share share) {
        RegisterItem key = new RegisterItem();
        key.setCustomer(customer);
        key.setShare(share);
        return map.get(key);
    }
}
