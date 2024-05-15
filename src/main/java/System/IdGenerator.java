package System;

import java.util.Random;

public interface IdGenerator {
    // many classes will implement Account abstract class which admin , borrower , customer inherits from it ,,, Transaction and order class
    // access modifiers all small letters
    default String getRandomId(int lower_bound , int upper_bound){ // public by default
        Random random_no = new Random();
        int randomValue = random_no.nextInt((upper_bound - lower_bound) + 1) + lower_bound;
        //return "#" + random_no.nextInt(upper_bound - lower_bound + 1) + lower_bound;
        return "#" + randomValue;

    }
    boolean isIdExists(String id); // public abstract by default

}
