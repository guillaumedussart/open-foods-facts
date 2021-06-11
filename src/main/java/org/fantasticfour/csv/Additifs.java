package org.fantasticfour.csv;

public class Additifs {
    private String name;

    public Additifs() {
    }

    public Additifs(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Additifs{" +
                "name='" + name + '\'' +
                '}';
    }
}
