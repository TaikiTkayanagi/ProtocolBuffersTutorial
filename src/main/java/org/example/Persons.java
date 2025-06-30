package org.example;

import java.util.List;

public class Persons {
    private int[] ids;
    private String[] names;

    public Persons(int[] ids, String[] names){
        this.ids = ids;
        this.names = names;
    }

    public int[] getIds() {
        return ids;
    }

    public String[] getNames() {
        return names;
    }
}
