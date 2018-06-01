package models;

import org.junit.After;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TeamTest {


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
        List<String> members = testTeam.getMembers();
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
}