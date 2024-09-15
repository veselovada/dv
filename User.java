package avto2;

import java.util.Random;

class User {
    String firstName;
    String lastName;
    double bankAccount;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bankAccount = new Random().nextDouble() * (5000000 - 300000) + 300000; // случайный баланс
    }
}