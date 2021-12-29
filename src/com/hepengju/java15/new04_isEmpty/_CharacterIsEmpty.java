package com.hepengju.java15.new04_isEmpty;

import org.junit.Test;

public class _CharacterIsEmpty {

    @Test
    public void testIsEmpty() {
        String name = " ";
        System.out.println(name.isEmpty()); // false
        System.out.println(name.isBlank()); // true
    }
}
