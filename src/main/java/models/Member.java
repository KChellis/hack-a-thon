package models;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String name;
    private static List<Member> instances = new ArrayList<>();
    private int id;

    public Member(String name) {
        this.name = name;
        instances.add(this);
        id = instances.size();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static List<Member> getAll(){
        return instances;
    }

    public static void clearAllMembers(){
        instances.clear();
    }

    public void updateMemberName(String newName){
        name = newName;
    }

    public void deleteMember(){
        instances.remove(id-1);
    }
}

