package com.advancewars.server.exception;

public class PartieNotFoundException extends RuntimeException {
	public   PartieNotFoundException(int partieId) {
		super("could not find Partie '" + partieId + "'.");
	}
}
