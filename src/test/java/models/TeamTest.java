package models;

import org.junit.Test;

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


}