package com.example.emma_nolan;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddPatientControllerTest {
    private Patient test1, test2, test3;

    @BeforeEach
    void setUp() {
        test1 = new Patient("Esther Boland", "1234567A", "2021-11-15", "Kilkenny", "Ireland", 872389334);
        test2 = new Patient("Patrick Nolan", "1123456A", "2021-11-15", "Kilkenny", "Ireland", 872687301);
        test3 = new Patient("Emma Nolan",    "1223456A",    "2021-11-15","Wexford","Ireland", 852833718);
    }

    @AfterEach
    void tearDown() {
        test1 = test2 = test3 =  null;

    }

    @Test
    void name() {
        assertEquals("Esther Boland", test1.getName());
        assertEquals("Patrick Nolan", test2.getName());
        assertEquals("Emma Nolan", test3.getName());
    }

    @Test
    void phonenum() {
        assertEquals(872389334, test1.getPhoneNum());
        assertEquals(872687301, test2.getPhoneNum());
        assertEquals(852833718, test3.getPhoneNum());
    }

    @Test
    void ppsn() {
        assertEquals("1234567A", test1.getPpsn());
        assertEquals("1123456A", test2.getPpsn());
        assertEquals("1223456A", test3.getPpsn());
    }
}