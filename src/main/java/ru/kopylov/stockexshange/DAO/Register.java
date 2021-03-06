package ru.kopylov.stockexshange.DAO;

import ru.kopylov.stockexshange.model.Customer;
import ru.kopylov.stockexshange.model.RegisterItem;
import ru.kopylov.stockexshange.model.Share;

import java.util.List;

/**
 * Created by se on 04.09.2018.
 */
public interface Register {
    void add(RegisterItem registerItem);
    RegisterItem find(Customer customer, Share share);
    List<RegisterItem> find(Customer customer);
    long getShareTotalBalance(Share share);
}
