package dao;

import models.Team;

import java.util.List;

public interface TeamDao {

    //LIST
    List<Team> getAll();

    //CREATE
    void add (Team team);

    //READ
    Team findById(int id);

    //UPDATE
    void update(int id, String name, String description);
    void updateIsFull(int id, boolean isFull);

    //DELETE
    void deleteById(int id);
    void clearAllTeams();
}
