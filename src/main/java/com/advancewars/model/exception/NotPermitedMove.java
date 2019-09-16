package com.advancewars.model.exception;

public class NotPermitedMove extends RuntimeException {
    public NotPermitedMove() {
        super("This move is Not Permitted");
    }
}
