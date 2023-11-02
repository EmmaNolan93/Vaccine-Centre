package com.example.emma_nolan;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppiomentStatusControllerTest {
    private VAppointment test1, test2, test3;

    @BeforeEach
    void setUp() {
        test1 = new VAppointment("Wexford", "2021-11-15", "16.00", "p", "K1", "122356A", "123", "E");
        test2 = new VAppointment("Wexford", "2021-11-15", "13.00", "Kilkenny", "K1", "123456A", "123", "E");
        test3 = new VAppointment("Wexford",    "2021-11-15",    "15.00","Wexford","K1", "1123456A", "123", "E");

    }

    @AfterEach
    void tearDown() {
        test1 = test2 = test3 = null;
    }

}