package dao;

import models.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oTeamDaoTest {
    private Sql2oTeamDao teamDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        teamDao = new Sql2oTeamDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add_setsId() {
        Team testTeam = setupTeam();
        teamDao.add(testTeam);
        assertEquals(1, testTeam.getId());
    }

    @Test
    public void getAll_returnsEmptyListIfNoTeams() {
        assertEquals(0, teamDao.getAll().size());
    }

    @Test
    public void getAll_returnsAllTeams() {
        Team testTeam = setupTeam();
        Team testTeam2 = setupTeam();
        teamDao.add(testTeam);
        teamDao.add(testTeam2);
        assertEquals(2, teamDao.getAll().size());
    }

    @Test
    public void findById_returnsCorrectTeam() {
        Team testTeam = setupTeam();
        teamDao.add(testTeam);
        Team foundTeam = teamDao.findById(1);
        assertEquals(testTeam.getName(), foundTeam.getName());
    }

    @Test
    public void update_changesNameAndDescription() {
        Team testTeam = setupTeam();
        teamDao.add(testTeam);
        teamDao.update(1, "new team name", "new description");
        assertNotEquals(testTeam.getName(), teamDao.findById(1).getName());
        assertNotEquals(testTeam.getDescription(), teamDao.findById(1).getDescription());
    }

    @Test
    public void updateIsFull_updatesIsFull() {
        Team testTeam = setupTeam();
        teamDao.add(testTeam);
        teamDao.updateIsFull(1, true);
        assertTrue(teamDao.findById(1).isIsFull());
    }

    @Test
    public void deleteById_deletesTeam() {
        Team testTeam = setupTeam();
        Team testTeam2 = setupTeam();
        teamDao.add(testTeam);
        teamDao.add(testTeam2);
        teamDao.deleteById(1);
        assertEquals(1, teamDao.getAll().size());
        assertEquals(2, teamDao.getAll().get(0).getId());
    }

    @Test
    public void clearAllTeams_deletesAllTeams() {
        Team testTeam = setupTeam();
        Team testTeam2 = setupTeam();
        teamDao.add(testTeam);
        teamDao.add(testTeam2);
        teamDao.clearAllTeams();
        assertEquals(0, teamDao.getAll().size());
    }

    public Team setupTeam(){
        return new Team("Awesome Team", "Epicodus students");
    }
}