package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class TeamTest {

    @Test
    public void newTeam_instantiatesCorrectly_true() {
        Team testTeam = new Team("Epicodus", "A group of Java Students");
        assertTrue(testTeam instanceof Team);
    }
}