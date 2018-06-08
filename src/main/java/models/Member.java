package models;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String name;
    private int teamId;
    private int id;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}

