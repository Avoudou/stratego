package gameSystems;

import java.util.ArrayList;

import abstractGameComponents.Player;
import abstractGameComponents.StrategoGame;
import events.StrategoAbstractEvent;
import events.StrategoMoveEvent;
import gameObjects.PieceType;
import gameObjects.StrategoPiece;
import gameObjects.TerrainType;

public class MoveSystem {

	public void moveActivePiece(StrategoGame aGame, StrategoAbstractEvent anEvent) {
		
		StrategoMoveEvent trueEvent = (StrategoMoveEvent) anEvent;
		int targetXcoord = aGame.getRuntimeData().getActivePiece().getxPos() + trueEvent.getdX();
		int targetYcoord = aGame.getRuntimeData().getActivePiece().getyPos() + trueEvent.getdY();
		System.out.println("move system input target coord x  " + targetXcoord);
		System.out.println("move system input target coord y  " + targetYcoord);
		System.out.println("MOVE SYSTEM RUNNING");
		if (!checkIfInsideBoard(targetXcoord, targetYcoord, aGame)) {
			System.out.println("illegal Coords");
			return;
		}

		if (!checkTileOccupied(aGame, targetXcoord, targetYcoord)) {
			System.out.println("tile taken  ");
			return;
		}
		if (checkIfLake(aGame, targetXcoord, targetYcoord)) {
			System.out.println("tile  is a lake");
			return;
		}
		if (!isActivePieceMovable(aGame)) {
			return;
		}

		StrategoPiece movingPiece = aGame.getRuntimeData().getActivePiece();
		System.out.println("old w x pos " + movingPiece.getxPos() + " old y pos " + movingPiece.getxPos());
		aGame.getBoard().getBoardStracture()[movingPiece.getyPos()][movingPiece.getxPos()].setOccupyingPiece(null);
		movingPiece.setxPos(targetXcoord);
		movingPiece.setyPos(targetYcoord);
		aGame.getBoard().getBoardStracture()[targetYcoord][targetXcoord].setOccupyingPiece(movingPiece);
		System.out.println("new x pos " + movingPiece.getxPos() + " new y pos " + movingPiece.getyPos());
		System.out.println("activePieceNewPosition x  " + aGame.getRuntimeData().getActivePiece().getxPos());
		System.out.println("activePieceNewPosition y  " + aGame.getRuntimeData().getActivePiece().getyPos());
		System.out.println(movingPiece);
		System.out.println(aGame.getRuntimeData().getActivePiece());


	}

	public void moveActivePieceForAttack(StrategoGame aGame, StrategoAbstractEvent anEvent) {
		StrategoMoveEvent trueEvent = (StrategoMoveEvent) anEvent;
		int targetXcoord = aGame.getRuntimeData().getActivePiece().getxPos() + trueEvent.getdX();
		int targetYcoord = aGame.getRuntimeData().getActivePiece().getyPos() + trueEvent.getdY();

		if (!checkIfInsideBoard(targetXcoord, targetYcoord, aGame)) {
			System.out.println("illegal Coords");
			return;
		}
		if (checkIfLake(aGame, targetXcoord, targetYcoord)) {
			System.out.println("tile  is a lake");
			return;
		}


		if (checkTileOccupied(aGame, targetXcoord, targetYcoord)) {
			System.out.println("noOneToAttack  ");
			return;
		}
		if (!checkValidOwnerships(aGame, targetXcoord, targetYcoord)) {
			System.out.println("cant attack your own units");
			return;

		}
		if (!isActivePieceMovable(aGame)) {
			return;
		}

		StrategoPiece movingPiece = aGame.getRuntimeData().getActivePiece();
		aGame.getBoard().getBoardStracture()[movingPiece.getyPos()][movingPiece.getxPos()].setOccupyingPiece(null);
		movingPiece.setxPos(targetXcoord);
		movingPiece.setyPos(targetYcoord);
		aGame.getRuntimeData().setAttackToResolve(true);

	}

	private boolean checkTileOccupied(StrategoGame aGame, int targetXcoord, int targetYcoord) {
		return (aGame.getBoard().getBoardStracture()[targetYcoord][targetXcoord].getOccupyingPiece() == null);
	}

	private boolean checkIfInsideBoard(int x, int y,StrategoGame aGame) {


		return (y >= 0) && (x >= 0) && (x <= aGame.getBoard().getBoardStracture()[0].length - 1)
				&& (y <= aGame.getBoard().getBoardStracture().length - 1);

	}

	private boolean checkIfLake(StrategoGame aGame, int targetXcoord, int targetYcoord) {
		return (aGame.getBoard().getBoardStracture()[targetYcoord][targetXcoord].getTerrainType() == TerrainType.LAKE);

	}

	private boolean checkValidOwnerships(StrategoGame aGame, int targetXcoord, int targetYcoord){
		StrategoPiece target=aGame.getBoard().getBoardStracture()[targetYcoord][targetXcoord].getOccupyingPiece();
		ArrayList<StrategoPiece> checkList = getActiveOpponent(aGame).getInGamePieces();
		for(int i=0;i<checkList.size();i++){
			if(target.getPieceID()==checkList.get(i).getPieceID()){
				return true;
				
			}
		}
		return false;
	}

	private Player getActiveOpponent(StrategoGame aGame) {
		if (aGame.getRuntimeData().getActivePlayer() == aGame.getPlayerNorth()) {
			return aGame.getPlayerSouth();
		}
		return aGame.getPlayerNorth();
	}

	private boolean isActivePieceMovable(StrategoGame aGame) {
		if (aGame.getRuntimeData().getActivePiece().getPieceType() == PieceType.BOMB
				|| aGame.getRuntimeData().getActivePiece().getPieceType() == PieceType.FLAG) {
			System.out.println("flags and bombs cant move");
			return false;
		}
		return true;
	}

}
