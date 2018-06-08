package models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private String description;
    private boolean isFull = false;
    private int id;

    public Team(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public boolean isIsFull() {
        return isFull;
    }

    public void setId(int id) {
        this.id = id;
    }
}
