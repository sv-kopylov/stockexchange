package ru.kopylov.stockexshange.ioc;

import ru.kopylov.stockexshange.Exceptions.NoSuchDefinitionException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by se on 05.09.2018.
 */
public class Context {
    private Map<Class, Object> map;
    public void add(Object obj){
        map.put(obj.getClass(), obj);
    }

    public Object lookup(Class key) throws NoSuchDefinitionException {
        Object result =  map.get(key);
        if(result==null){
            throw new NoSuchDefinitionException("No definition of "+ key.getCanonicalName() +"found in context");
        }
        return result;
    }

    private static Context instance = new Context();
    private Context(){
        map = new HashMap<>();
    }
    public static Context getInstance(){
        if(instance==null){
            instance=new Context();
        }
        return instance;
    }
}
