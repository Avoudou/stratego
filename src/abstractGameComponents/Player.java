package abstractGameComponents;

import gameObjects.StrategoPiece;

import java.util.ArrayList;

public class Player {
	private ArrayList<StrategoPiece> inGamePieces;
	private ArrayList<StrategoPiece> unDeployedPieces;

	public Player(ArrayList<StrategoPiece> inGamePieces) {
		super();
		this.inGamePieces = inGamePieces;
		this.unDeployedPieces = new ArrayList<StrategoPiece>(this.inGamePieces);
	}

	public ArrayList<StrategoPiece> getInGamePieces() {
		return inGamePieces;
	}

	public void setInGamePieces(ArrayList<StrategoPiece> inGamePieces) {
		this.inGamePieces = inGamePieces;
	}

	public ArrayList<StrategoPiece> getUnDeployedPieces() {
		return unDeployedPieces;
	}

	public void setUnDeployedPieces(ArrayList<StrategoPiece> unDeployedPieces) {
		this.unDeployedPieces = unDeployedPieces;
	}

}
