package ru.kopylov.stockexshange.DAO;

import ru.kopylov.stockexshange.model.Customer;
import ru.kopylov.stockexshange.model.RegisterItem;
import ru.kopylov.stockexshange.model.Share;

/**
 * Created by se on 04.09.2018.
 */
public interface Register {
    void addItem(RegisterItem registerItem);
    RegisterItem findItem(Customer customer, Share share);
}