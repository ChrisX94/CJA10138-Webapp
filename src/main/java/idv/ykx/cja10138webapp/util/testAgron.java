package idv.ykx.cja10138webapp.util;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class testAgron {
    public static void main(String[] args) {

        String password = "miduki.love";
        PasswordCovert pc = new PasswordCovert();
        String hashed = pc.hashing(password);

        System.out.println(hashed);

//        String input = "Im879487";
        String input = "lovelyme";
        System.out.println(pc.passwordVerify(hashed, input));


    }



}


