package ru.kopylov.stockexshange.settings;

import ru.kopylov.stockexshange.model.Share;

import java.util.ArrayList;
import java.util.List;

/**

 */
public class Settings {
    /*
    * List of available shares
    * Must be before app run according to task requirements
    */
    List<Share> list = new ArrayList<>();
    {
        list.add(new Share("A", 1));
        list.add(new Share("B", 2));
        list.add(new Share("C", 3));
        list.add(new Share("D", 4));
    }

    static Settings instance;

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public List<Share> getShares() {
        return list;
    }
}
