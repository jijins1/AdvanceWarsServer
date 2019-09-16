package com.advancewars.model.exception;

public class NotPermittedAttack extends RuntimeException {
    public NotPermittedAttack() {
        super("This attack is Not Permitted");
    }
}
