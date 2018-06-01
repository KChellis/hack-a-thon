package models;

import org.junit.After;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TeamTest {

    @After
    public void tearDown() {
        Team.clearAllTeams();
    }


    @Test
    public void newTeam_instantiatesCorrectly_true() {
        Team testTeam = new Team("Epicodus", "A group of Java Students");
        assertTrue(testTeam instanceof Team);
    }

    @Test
    public void newTeam_setsName_Epicodus() {
        Team testTeam = new Team("Epicodus", "A group of Java Students");
        assertEquals("Epicodus", testTeam.getName());
    }

    @Test
    public void newTeam_setsDescription_Epicodus() {
        Team testTeam = new Team("Epicodus", "A group of Java Students");
        assertEquals("A group of Java Students", testTeam.getDescription());
    }

    @Test
    public void newTeam_setsMembersToEmptyArray_Epicodus() {
        Team testTeam = new Team("Epicodus", "A group of Java Students");
        List<Member> members = testTeam.getMembers();
        assertEquals(0, members.size());
    }

    @Test
    public void addMember_addsMemberNameToMembers_KristenChellis() {
        Team testTeam = new Team("Epicodus", "A group of Java Students");
        testTeam.addMember("Kristen Chellis");
        assertEquals(1, testTeam.getMembers().size());
        assertEquals("Kristen Chellis", testTeam.getMembers().get(0));
    }

    @Test
    public void getAll_returnsAllTeamsCorrectly_2() {
        Team testTeam1 = new Team("Epicodus", "A group of Java Students");
        Team testTeam2 = new Team("New Relic", "A group of senior developers");
        assertEquals(2, Team.getAll().size());

    }

    @Test
    public void getAll_containsAllTeams_true() {
        Team testTeam1 = new Team("Epicodus", "A group of Java Students");
        Team testTeam2 = new Team("New Relic", "A group of senior developers");
        assertTrue(Team.getAll().contains(testTeam1));
        assertTrue(Team.getAll().contains(testTeam2));
    }

    @Test
    public void clearAllTeams_clearsInstances_0() {
        Team testTeam1 = new Team("Epicodus", "A group of Java Students");
        Team testTeam2 = new Team("New Relic", "A group of senior developers");
        Team.clearAllTeams();
        assertEquals(0, Team.getAll().size());
    }

    @Test
    public void updateTeamName_changesTeamName() {
        Team testTeam = new Team("Epicodus", "A group of Java Students");
        String formerName = testTeam.getName();
        String formerDescription =testTeam.getDescription();
        List<Member> formerMembers = testTeam.getMembers();


        testTeam.updateTeamName("Epicodus alums");

        assertEquals(formerMembers, testTeam.getMembers());
        assertEquals(formerDescription, testTeam.getDescription());
        assertNotEquals(formerName, testTeam.getName());
    }

    @Test
    public void getId_teamsInstantiateWithAnID_1() throws Exception{
        Team testTeam = new Team("Epicodus", "A group of Java Students");
        assertEquals(1, testTeam.getId());
    }

    @Test
    public void getById_returnsCorrectTeam_true() {
        Team testTeam = new Team("Epicodus", "A group of Java Students");
        assertEquals(1, Team.findById(testTeam.getId()).getId());
    }
    @Test
    public void getById_returnsCorrectTeamWhenMoreThanOneTeam_true() {
        Team testTeam1 = new Team("Epicodus", "A group of Java Students");
        Team testTeam2 = new Team("New Relic", "A group of senior developers");
        assertEquals(2, Team.findById(testTeam2.getId()).getId());
    }

    @Test
    public void updateTeamDescription_changesTeamDescription() {
        Team testTeam = new Team("Epicodus", "A group of Java Students");
        String formerName = testTeam.getName();
        String formerDescription =testTeam.getDescription();
        List<Member> formerMembers = testTeam.getMembers();


        testTeam.updateTeamDescription("A group of JavaScript Students");

        assertEquals(formerMembers, testTeam.getMembers());
        assertEquals(formerName, testTeam.getName());
        assertNotEquals(formerDescription, testTeam.getDescription());
    }

    @Test
    public void newTeam_instantiatesWithFullFalse_false() {
        Team testTeam = new Team("Epicodus", "A group of Java Students");
        assertFalse(testTeam.isFull());
    }

    @Test
    public void setFull_changesFull_true() {
        Team testTeam = new Team("Epicodus", "A group of Java Students");
        testTeam.setFull(true);
        assertTrue(testTeam.isFull());
    }
}