package gameSystems;

import gameObjects.PieceType;
import gameObjects.StrategoPiece;

import java.util.ArrayList;

import abstractGameComponents.Player;
import abstractGameComponents.StrategoGame;



public class CheckVictorySystem extends MoveSystem {
	MoveGenerator moveGenerator = new MoveGenerator();

	public boolean checkIfFlagCaptured(StrategoGame aGame) {

		Player opponet = super.getActiveOpponent(aGame);
		ArrayList<StrategoPiece> checkList = opponet.getInGamePieces();
		for (int i = 0; i < checkList.size(); i++) {
			if (checkList.get(i).getPieceType() == PieceType.FLAG) {
				return false;
			}
		}
		System.out.println("game over " + aGame.getRuntimeData().getActivePlayer() + "  wins");
		return true;
	}

	public boolean checkForLeagalMoves(StrategoGame aGame) {
		Player player = aGame.getRuntimeData().getActivePlayer();
		ArrayList<StrategoPiece> checkList = player.getInGamePieces();
		for (int i = 0; i < checkList.size(); i++) {
			StrategoPiece testPiece = checkList.get(i);
			if (testPiece.getPieceType() != PieceType.BOMB || testPiece.getPieceType() != PieceType.FLAG) {


			if (checkIfLegalToMove(aGame, testPiece.getyPos(), testPiece.getxPos() + 1)) {
				return true;
			}
			if (checkIfLegalToMove(aGame, testPiece.getyPos(), testPiece.getxPos() - 1)) {
				return true;
			}
			if (checkIfLegalToMove(aGame, testPiece.getyPos() - 1, testPiece.getxPos())) {
				return true;
			}
			if (checkIfLegalToMove(aGame, testPiece.getyPos() + 1, testPiece.getxPos())) {
				return true;
			}
			}
		}
		System.out.println("no possible moves   : you loose");
		return false;
	}

	private boolean checkIfLegalToMove(StrategoGame aGame, int yCoord, int xCoord) {
		
		
		
		return super.checkIfInsideBoard(xCoord, yCoord, aGame) && !super.checkIfLake(aGame, xCoord, yCoord)
				&& (super.checkTileFree(aGame, xCoord, yCoord) || super.checkValidOwnerships(aGame, xCoord, yCoord));
	}

}
