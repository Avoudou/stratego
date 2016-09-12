package abstractGameComponents;

import factorys.BoardFactory;
import factorys.GamePieceFactory;
import gameObjects.StrategoBoard;
import gameObjects.StrategoPiece;

import java.util.ArrayList;

public class StrategoGame {

	private StrategoBoard board;

	// private ArrayList<Player> playerList;

	private Player playerNorth;
	private Player playerSouth;



	private RuntimeData runtimeData;

	// private HashMap<StrategoPiece, Player> inGamePieceOwnershipMap = new HashMap<StrategoPiece, Player>();

	public StrategoGame() {
		
		BoardFactory boardFactory = new BoardFactory();
		this.board = boardFactory.createBoard();
		GamePieceFactory pieceFactory = new GamePieceFactory();
		ArrayList<StrategoPiece> gamePiecesPlayerA = pieceFactory.createPlayerPieces();
		ArrayList<StrategoPiece> gamePiecesPlayerB = pieceFactory.createPlayerPieces();
		playerNorth = new Player(gamePiecesPlayerA);
		playerSouth = new Player(gamePiecesPlayerB);
		runtimeData = new RuntimeData();
		runtimeData.setActivePlayer(playerNorth);
		runtimeData.setDeploymentPhase(true);

	}

	public StrategoBoard getBoard() {
		return board;
	}

	public RuntimeData getRuntimeData() {
		return runtimeData;
	}

	public Player getPlayerSouth() {
		return playerSouth;
	}

	public void setPlayerSouth(Player playerB) {
		this.playerSouth = playerB;
	}

	public Player getPlayerNorth() {
		return playerNorth;
	}

	public void setPlayerNorth(Player playerA) {
		this.playerNorth = playerA;
	}

}
