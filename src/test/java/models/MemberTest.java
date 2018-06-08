package models;

import org.junit.After;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MemberTest {

    @Test
    public void newMember_instantiatesCorrectly_true() {
        Member testMember = new Member("Kristen Chellis");
        assertTrue(testMember instanceof Member);
    }

    @Test
    public void newMember_setsName_KristenChellis() {
        Member testMember = new Member("Kristen Chellis");
        assertEquals("Kristen Chellis", testMember.getName());
    }

    @Test
    public void setId_setsId_1() {
        Member testMember = new Member("Kristen Chellis");
        testMember.setId(1);
        assertEquals(1, testMember.getId());
    }

    @Test
    public void setTeamId_setsTeamId() {
        Member testMember = new Member("Kristen Chellis");
        testMember.setTeamId(1);
        assertEquals(1, testMember.getTeamId());
    }
}