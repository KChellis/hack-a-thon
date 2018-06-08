package models;

import org.junit.After;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MemberTest {

    @Test
    public void newMember_instantiatesCorrectly_true() {
        Member testMember = setupMember();
        assertTrue(testMember instanceof Member);
    }

    @Test
    public void newMember_setsName_KristenChellis() {
        Member testMember = setupMember();
        assertEquals("Kristen Chellis", testMember.getName());
    }

    @Test
    public void newMember_setsEmail_peanutster() {
        Member testMember = setupMember();
        assertEquals("peanutster@gmail.com", testMember.getEmail());
    }

    @Test
    public void newMember_setsPhone_5037204196() {
        Member testMember = setupMember();
        assertEquals("503-720-4196", testMember.getPhone());
    }

    @Test
    public void setId_setsId_1() {
        Member testMember = setupMember();
        testMember.setId(1);
        assertEquals(1, testMember.getId());
    }

    @Test
    public void setTeamId_setsTeamId() {
        Member testMember = setupMember();
        testMember.setTeamId(1);
        assertEquals(1, testMember.getTeamId());
    }

    public Member setupMember(){
        return  new Member("Kristen Chellis", "peanutster@gmail.com", "503-720-4196");
    }
}