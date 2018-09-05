package ru.kopylov.stockexshange.Exceptions;

import org.apache.log4j.Logger;

/**
 * Created by se on 05.09.2018.
 */
public class ExceptionHandler {
    private static Logger logger = Logger.getLogger(ExceptionHandler.class);
    public void handleException(Exception e){
        logger.warn("Exception occured: " +e.getMessage());
    }
}
