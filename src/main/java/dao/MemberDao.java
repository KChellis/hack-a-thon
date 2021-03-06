package dao;

import models.Member;

import java.util.List;

public interface MemberDao {
    List<Member> getAll();

    //CREATE
    void add (Member member);

    //READ
    Member findById(int id);
    List<Member> findByTeam(int teamId);

    //UPDATE
    void update(int id, String name, String email, String phone, int teamId);

    //DELETE
    void deleteById(int id);
    void clearAllMembers();
}
