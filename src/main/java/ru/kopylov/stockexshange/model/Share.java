package ru.kopylov.stockexshange.model;

/**
 *
 */
public class Share {
    private String name;

    public Share(String name) {
        this.name = name;
    }

    public Share() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
