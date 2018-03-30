package advanceWar.partie;

public class PartieNotFoundException extends RuntimeException {
	public   PartieNotFoundException(int partieId) {
		super("could not find Partie '" + partieId + "'.");
	}
}
