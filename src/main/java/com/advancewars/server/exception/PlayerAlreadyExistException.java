package com.advancewars.server.exception;

public class PlayerAlreadyExistException extends RuntimeException {
	public PlayerAlreadyExistException(String userId) {
		super("Player '" + userId + "' already exist");
	}
}
