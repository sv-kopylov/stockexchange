package ru.kopylov.stockexshange.DAO;

import ru.kopylov.stockexshange.model.Share;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by se on 05.09.2018.
 */
public class ShareDAO {
    private final List<Share> list = new ArrayList<>();
    {
        list.add(new Share("A", 1));
        list.add(new Share("B", 2));
        list.add(new Share("C", 3));
        list.add(new Share("D", 4));
    }
    public Share get(String name){
         for(Share s: list){
            if(s.getName().equals(name)){
                return s;
            }
        }
        return null;
    }
    public Share get(int index){
        for(Share s: list){
            if(s.getIndex()==index){
                return s;
            }
        }
        return null;
    }
}
