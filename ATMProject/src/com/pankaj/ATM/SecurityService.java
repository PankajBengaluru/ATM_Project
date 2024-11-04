package com.pankaj.ATM;

public class SecurityService {
    private final String correctPin = "1234";

    public boolean authenticate(String pin) {
        return correctPin.equals(pin);
    }
}
