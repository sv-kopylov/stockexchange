package ru.kopylov.stockexshange.DAO;

import ru.kopylov.stockexshange.model.Share;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by se on 05.09.2018.
 */
public class ShareDAO {
    private List<Share> list= new ArrayList<>();


    public Share get(String name){
         for(Share s: list){
            if(s.getName().equals(name)){
                return s;
            }
        }
        return null;
    }
    public Share get(int name){
        for(Share s: list){
            if(s.getName().equals(name)){
                return s;
            }
        }
        return null;
    }
}
