package ru.kopylov.stockexshange.settings;

import ru.kopylov.stockexshange.model.Share;

import java.util.ArrayList;
import java.util.List;

/**

 */
public class Settings {
    /*
    * List of availabel shares
    * Must contain all shares mentioned in files
    */
    List<Share> list = new ArrayList<>();

    private Settings() {
        list.add(new Share("A", 1));
        list.add(new Share("B", 2));
        list.add(new Share("C", 3));
        list.add(new Share("D", 4));
// need for fast search
        list.sort(Share::compareTo);
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
