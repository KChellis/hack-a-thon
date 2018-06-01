package models;

import org.junit.After;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MemberTest {

    @After
    public void tearDown() throws Exception {
        Member.clearAllMembers();
    }

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
    public void newMember_setsId_1() {
        Member testMember = new Member("Kristen Chellis");
        assertEquals(1, testMember.getId());
    }

    @Test
    public void getAll_returnsAllMembersCorrectly_2() {
        Member testMember1 = new Member("Kristen Chellis");
        Member testMember2 = new Member("Kayl Eubanks");
        assertEquals(2, Member.getAll().size());

    }

    @Test
    public void getAll_containsAllMembers_true() {
        Member testMember1 = new Member("Kristen Chellis");
        Member testMember2 = new Member("Kayl Eubanks");
        assertTrue(Member.getAll().contains(testMember1));
        assertTrue(Member.getAll().contains(testMember2));
    }

    @Test
    public void clearAllMembers_clearsInstances_0() {
        Member testMember1 = new Member("Kristen Chellis");
        Member testMember2 = new Member("Kayl Eubanks");
        Member.clearAllMembers();
        assertEquals(0, Member.getAll().size());
    }

    @Test
    public void updateMemberName_changesMemberName() {
        Member testMember = new Member("Kristen Chellis");
        String formerName = testMember.getName();
        testMember.updateMemberName("Kayl Eubanks");
        assertNotEquals(formerName, testMember.getName());
    }

    @Test
    public void deleteMember_DeletesASpecificMember() throws Exception {
        Member testMember1 = new Member("Kristen Chellis");
        Member testMember2 = new Member("Kayl Eubanks");
        testMember1.deleteMember();
        assertEquals(1, Member.getAll().size());
        assertEquals(2, Member.getAll().get(0).getId());
    }
}