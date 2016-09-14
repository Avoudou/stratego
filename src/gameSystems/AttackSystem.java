package gameSystems;

import java.util.ArrayList;

import abstractGameComponents.PieceHierarchyData;
import abstractGameComponents.Player;
import abstractGameComponents.StrategoGame;
import events.StrategoAbstractEvent;
import gameObjects.PieceType;
import gameObjects.StrategoPiece;

public class AttackSystem {

	public void resolveAttack(StrategoGame aGame, StrategoAbstractEvent anEvent) {

		if (!aGame.getRuntimeData().isAttackToResolve()) {
			return;
		}
		StrategoPiece attackingPiece = aGame.getRuntimeData().getActivePiece();
		StrategoPiece defendingPiece = aGame.getBoard().getBoardStracture()[attackingPiece.getyPos()][attackingPiece
				.getxPos()].getOccupyingPiece();


		// System.out.println("attack event x Coord :" + attackingPiece.getxPos());
		// System.out.println("attack event y Coord :" + attackingPiece.getyPos());
		//
		// System.out.println("def event x Coord :" + defendingPiece.getxPos());
		// System.out.println("def event y Coord :" + defendingPiece.getyPos());
		//
		// System.out.println((PieceHierarchyData.pieceLvlMap.get(attackingPiece.getPieceType()))
		// + " attackingPiece power");
		// System.out.println((PieceHierarchyData.pieceLvlMap.get(defendingPiece.getPieceType()))
		// + " defendingPiece power");

		if (attackWins(attackingPiece, defendingPiece)) {
			System.out
.println("Attacker wins   " + attackingPiece.getPieceType() + " vs "
					+ defendingPiece.getPieceType());
			aGame.getRuntimeData().setGetOutInfo(
					"Attacker wins   " + attackingPiece.getPieceType() + " vs " + defendingPiece.getPieceType());
			aGame.getBoard().getBoardStracture()[attackingPiece.getyPos()][attackingPiece.getyPos()].setOccupyingPiece(attackingPiece);
			
			defendingPiece.setxPos(-100);
			defendingPiece.setyPos(-100);

			ArrayList<StrategoPiece> checkList = getActiveOpponent(aGame).getInGamePieces();

			removePieceFromGame(defendingPiece, checkList);
			aGame.getRuntimeData().setAttackToResolve(false);
		} else if (combatIsDraw(attackingPiece, defendingPiece)) {

			System.out.println("draw");
			aGame.getBoard().getBoardStracture()[attackingPiece.getyPos()][attackingPiece.getyPos()]
					.setOccupyingPiece(null);
			
			attackingPiece.setxPos(-100);
			attackingPiece.setyPos(-100);
			defendingPiece.setxPos(-100);
			defendingPiece.setyPos(-100);

			ArrayList<StrategoPiece> checkList = getActiveOpponent(aGame).getInGamePieces();
			removePieceFromGame(defendingPiece, checkList);
			checkList = aGame.getRuntimeData().getActivePlayer().getInGamePieces();
			removePieceFromGame(attackingPiece, checkList);
			aGame.getRuntimeData().setActivePiece(null);
			aGame.getRuntimeData().setAttackToResolve(false);

		} else {
		System.out.println("defender wins");
		attackingPiece.setxPos(-100);
		attackingPiece.setyPos(-100);
		ArrayList<StrategoPiece> checkList = aGame.getRuntimeData().getActivePlayer().getInGamePieces();
		removePieceFromGame(attackingPiece, checkList);
			aGame.getRuntimeData().setActivePiece(null);
			aGame.getRuntimeData().setAttackToResolve(false);
		}
	}

	private boolean combatIsDraw(StrategoPiece attackingPiece, StrategoPiece defendingPiece) {
		return (int) (PieceHierarchyData.pieceLvlMap.get(attackingPiece.getPieceType())) == (int) (PieceHierarchyData.pieceLvlMap
				.get(defendingPiece.getPieceType()));
	}

	private boolean attackWins(StrategoPiece attackingPiece, StrategoPiece defendingPiece) {
		if (attackingPiece.getPieceType() == PieceType.SPY && defendingPiece.getPieceType() == PieceType.MARSHAL) {
			return true;
		}
		if (attackingPiece.getPieceType() == PieceType.MINER && defendingPiece.getPieceType() == PieceType.BOMB) {
			return true;
		}
		return (PieceHierarchyData.pieceLvlMap.get(attackingPiece.getPieceType())) > (PieceHierarchyData.pieceLvlMap
				.get(defendingPiece.getPieceType()));
	}

	private void removePieceFromGame(StrategoPiece defendingPiece, ArrayList<StrategoPiece> checkList) {
		for (int i = 0; i < checkList.size(); i++) {
			if (defendingPiece.getPieceID() == checkList.get(i).getPieceID()) {
				checkList.remove(i);

			}
		}
	}
	
	private Player getActiveOpponent(StrategoGame aGame) {
		if (aGame.getRuntimeData().getActivePlayer() == aGame.getPlayerNorth()) {
			return aGame.getPlayerSouth();
		}
		return aGame.getPlayerNorth();
	}
}
