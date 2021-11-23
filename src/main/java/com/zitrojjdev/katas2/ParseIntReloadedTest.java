package com.zitrojjdev.katas2;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class ParseIntReloadedTest {

    @Test
    public void fixedTests() {
        assertEquals(1 , ParseIntReloaded.parseInt("one"));
        assertEquals(20 , ParseIntReloaded.parseInt("twenty"));
        assertEquals(21 , ParseIntReloaded.parseInt("twenty-one"));
        assertEquals(21 , ParseIntReloaded.parseInt("twenty one"));
        assertEquals(20000 , ParseIntReloaded.parseInt("twenty thousand"));
        assertEquals(20001 , ParseIntReloaded.parseInt("twenty thousand and one"));
        assertEquals(21012 , ParseIntReloaded.parseInt("twenty-one thousand and twelve"));
        assertEquals(989 , ParseIntReloaded.parseInt("nine hundred and eighty-nine"));
        assertEquals(246 , ParseIntReloaded.parseInt("two hundred forty-six"));
    }
}