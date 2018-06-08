package dao;

import models.Member;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oMemberDaoTest {
    private Sql2oMemberDao memberDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        memberDao = new Sql2oMemberDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add_setsId() {
        Member testMember = setupMember();
        memberDao.add(testMember);
        assertEquals(1, testMember.getId());
    }

    @Test
    public void getAll_returnsEmptyListIfNoMembers() {
        assertEquals(0, memberDao.getAll().size());
    }

    @Test
    public void getAll_returnsAllMembers() {
        Member testMember = setupMember();
        Member testMember2 = setupMember();
        memberDao.add(testMember);
        memberDao.add(testMember2);
        assertEquals(2, memberDao.getAll().size());
    }

    @Test
    public void findById_returnsCorrectMember() {
        Member testMember = setupMember();
        memberDao.add(testMember);
        Member foundMember = memberDao.findById(1);
        assertEquals(testMember.getName(), foundMember.getName());
    }

    @Test
    public void update_changesNameEmailPhoneAndTeamId() {
        Member testMember = setupMember();
        memberDao.add(testMember);
        memberDao.update(1, "new member name", "new email", "new phone", 2);
        assertNotEquals(testMember.getName(), memberDao.findById(1).getName());
        assertNotEquals(testMember.getEmail(), memberDao.findById(1).getEmail());
        assertNotEquals(testMember.getPhone(), memberDao.findById(1).getPhone());
        assertNotEquals(testMember.getTeamId(), memberDao.findById(1).getTeamId());
    }

    @Test
    public void deleteById_deletesMember() {
        Member testMember = setupMember();
        Member testMember2 = setupMember();
        memberDao.add(testMember);
        memberDao.add(testMember2);
        memberDao.deleteById(1);
        assertEquals(1, memberDao.getAll().size());
        assertEquals(2, memberDao.getAll().get(0).getId());
    }

    @Test
    public void clearAllMembers_deletesAllMembers() {
        Member testMember = setupMember();
        Member testMember2 = setupMember();
        memberDao.add(testMember);
        memberDao.add(testMember2);
        memberDao.clearAllMembers();
        assertEquals(0, memberDao.getAll().size());
    }

    @Test
    public void findByTeam_returnsMembersByTeam() {
        Member testMember = setupMember();
        Member testMember2 = setupMember();
        memberDao.add(testMember);
        memberDao.add(testMember2);
        Member testMember3 = new Member("sarah", "email", "phone", 2);
        memberDao.add(testMember3);
        assertEquals(2, memberDao.findByTeam(1).size());
    }

    public Member setupMember(){
        return  new Member("Kristen Chellis", "peanutster@gmail.com", "503-720-4196", 1);
    }
}