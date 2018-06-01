package models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private String description;
    private List<String> members = new ArrayList<>();
    private static ArrayList<Team> instances = new ArrayList<>();
    private int id;
    private boolean full = false;

    public Team(String name, String description){
        this.name = name;
        this.description = description;
        instances.add(this);
        id = instances.size();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getMembers() {
        return members;
    }

    public void addMember(String name){
        members.add(name);
    }

    public static ArrayList<Team> getAll() {
        return instances;
    }

    public static void clearAllTeams(){
        instances.clear();
    }

    public void updateTeamName(String newName){
        name = newName;
    }

    public int getId() {
        return id;
    }

    public static Team findById(int id){
        return instances.get(id-1);
    }

    public void updateTeamDescription(String description) {
        this.description = description;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }
}
