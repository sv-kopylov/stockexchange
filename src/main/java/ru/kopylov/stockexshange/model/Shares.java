package ru.kopylov.stockexshange.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Набор акций
 * singleton
 */
public class Shares {
    private Shares(){
        shares.add("A");
        shares.add("B");
        shares.add("C");
        shares.add("D");
    }
    private static Shares instance;
    final private List<String> shares = new ArrayList<>();
    public static Shares getInstance() {
        if(instance==null){
            instance=new Shares();
        }
        return instance;
    }
    public int getSize(){
        return shares.size();
    }

}
