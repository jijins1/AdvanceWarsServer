package advanceWar.player;

public class PlayerNotFoundException extends RuntimeException {
	
	
	public PlayerNotFoundException(int userId) {
		super("could not find Player '" + userId + "'.");
	}
	public PlayerNotFoundException(String userId) {
		super("could not find Player '" + userId + "'.");
	}
}
