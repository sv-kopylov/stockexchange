package ru.kopylov.stockexshange.load;

/**
 * Created by se on 05.09.2018.
 */
public interface Parser {
    String COLUMN_DELIMITER = "\t";
    int ORDERS_TABLE_COLUMNS = 5;
    String ORDERS_SALE_SYMBOL ="s";
    String ORDERS_BUY_SYMBOL ="b";
    void parse(String path);
    void init();


}
