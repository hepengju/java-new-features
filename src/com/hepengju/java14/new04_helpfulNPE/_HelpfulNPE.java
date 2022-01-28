package com.hepengju.java14.new04_helpfulNPE;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

/**
 * 改善的NPE异常提示
 */
public class _HelpfulNPE {

    @Test
    public void testNPE(){
        Pet pet = new Pet("silly dog");
        User user = new User("hepengju", pet);

        // java.lang.NullPointerException
        System.out.println(user.getPet().getName());

        // java.lang.NullPointerException: Cannot invoke "com.hepengju.java14.new04_helpfulNPE.Pet.getName()"
        // because the return value of "com.hepengju.java14.new04_helpfulNPE.User.getPet()" is null
        //user.setPet(null);
        //System.out.println(user.getPet().getName());

        // java.lang.NullPointerException: Cannot invoke "com.hepengju.java14.new04_helpfulNPE.User.getPet()"
        // because "user" is null
        user = null;
        System.out.println(user.getPet().getName());
    }
}

@Data @AllArgsConstructor
class User {
    private String name;
    private Pet pet;
}

@Data @AllArgsConstructor
class Pet {
    String name;
}
