package ru.kopylov.stockexshange.Exceptions;

/**
 * Created by se on 05.09.2018.
 */
public class CriticalException extends RuntimeException {
    public CriticalException(String message) {
        super(message);
    }
}
