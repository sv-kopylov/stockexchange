package ru.kopylov.stockexshange.load;

/**
 * Created by se on 05.09.2018.
 */
public interface Parcer {
    String COLUMN_DELIMITER = "\t";
    void parce(String path);
    void init();


}
