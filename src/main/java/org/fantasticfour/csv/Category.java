package org.fantasticfour.csv;

public class Category {

    private String name;

    public Category(String name) {
        this.name = name;
    }

    /**
     * get field
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * set field
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
