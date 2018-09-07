package ru.kopylov.stockexshange.DAO;

import ru.kopylov.stockexshange.model.Customer;
import ru.kopylov.stockexshange.model.RegisterItem;
import ru.kopylov.stockexshange.model.Share;

import java.util.*;

/**
 * Not very memory-friendly implementation, but
 * Optimized by performance
 *
 */

public class  RegisterImpl implements Register {

//  TODO  keys could be smaller
   private final Map<RegisterItem, RegisterItem> map = new HashMap<>();

    @Override
    public void add(RegisterItem registerItem) {
        map.put(registerItem, registerItem);

    }

    @Override
    public RegisterItem find(Customer customer, Share share) {
        RegisterItem key = new RegisterItem();
        key.setCustomer(customer);
        key.setShare(share);
        return map.get(key);
    }

    @Override
    public long getShareTotalBalance(Share share) {
      return   map.values()
              .stream()
              .filter(ri->ri.getShare().equals(share))
              .mapToLong(RegisterItem::getItemBalance)
              .sum();

    }

    public long count(){
        return map.size();
    }
}
