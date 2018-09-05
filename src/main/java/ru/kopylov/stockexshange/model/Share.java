package ru.kopylov.stockexshange.model;

/**
 *
 */
public class Share implements Comparable<Share> {
    private String name;
    private int index;

    public Share(String name, int index) {
        this.name = name;
        this.index = index;
    }

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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int compareTo(Share o) {
        return this.name.compareTo(o.name);
    }
}
