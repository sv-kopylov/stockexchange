package ru.kopylov.stockexshange.model;

/**
 *
 */
public class Share  {
    private String name;
    private int index;

    public Share(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public Share(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Share)) return false;

        Share share = (Share) o;

        if (index != share.index) return false;
        return name != null ? name.equals(share.name) : share.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + index;
        return result;
    }
}
