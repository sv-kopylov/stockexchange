package ru.kopylov.stockexshange.DAO;

import ru.kopylov.stockexshange.model.Share;
import ru.kopylov.stockexshange.settings.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by se on 05.09.2018.
 */
public class ShareDAO {
    private List<Share> list= Settings.getInstance().getShares();
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
