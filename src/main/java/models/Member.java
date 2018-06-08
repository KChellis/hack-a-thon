package models;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String name;
    private String email;
    private String phone;
    private int teamId;
    private int id;

    public Member(String name, String email, String phone, int teamId) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.teamId= teamId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
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

