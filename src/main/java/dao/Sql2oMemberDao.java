package dao;

import models.Member;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oMemberDao implements MemberDao{
    private final Sql2o sql2o;

    public Sql2oMemberDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Member> getAll() {
        String sql = "SELECT * FROM members";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(Member.class);
        }
    }

    @Override
    public void add(Member member) {
        String sql = "INSERT INTO members (name, email, phone, teamId) VALUES (:name, :email, :phone, :teamId)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(member)
                    .executeUpdate()
                    .getKey();
            member.setId(id);
        } catch(Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public Member findById(int id) {
        String sql = "SELECT * FROM members WHERE id = :id";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Member.class);
        }
    }

    @Override
    public List<Member> findByTeam(int teamId) {
        String sql = "SELECT * FROM members WHERE teamId = :teamId";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("teamId", teamId)
                    .executeAndFetch(Member.class);
        }
    }

    @Override
    public void update(int id, String name, String email, String phone, int teamId) {
        String sql = "UPDATE members SET (name, email, phone, teamId) = (:name, :email, :phone, :teamId) WHERE id = :id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("email", email)
                    .addParameter("phone", phone)
                    .addParameter("teamId", teamId)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from members WHERE id = :id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllMembers() {
        String sql = "DELETE from members";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

}
