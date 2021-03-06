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
    public void setId_setsId_1() throws Exception{
        Team testTeam = new Team("Epicodus", "A group of Java Students");
        testTeam.setId(1);
        assertEquals(1, testTeam.getId());
    }

    @Test
    public void newTeam_instantiatesWithFullFalse_false() {
        Team testTeam = new Team("Epicodus", "A group of Java Students");
        assertFalse(testTeam.isIsFull());
    }
    
}