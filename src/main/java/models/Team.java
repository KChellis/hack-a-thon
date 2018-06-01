package models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private String description;
    private List<String> members = new ArrayList<>();

    public Team(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
}
