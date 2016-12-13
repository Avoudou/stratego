package aiPack;

import events.StrategoAbstractEvent;
import gameLogic.SystemsManager;
import gameObjects.PieceType;
import gameObjects.StrategoPiece;

import java.util.ArrayList;

import abstractDefinitions.TreeNode;
import abstractGameComponents.PieceHierarchyData;
import abstractGameComponents.StrategoGame;
import abstractSearchComponents.Rules;

public class StrategoRules extends Rules<StrategoGame> {

	// private SystemsManager systemManager;

	public StrategoRules(SystemsManager systemManager){
		// this.systemManager = systemManager;
	}

	@Override
	public boolean isTerminal(TreeNode<StrategoGame, ?> aNode) {
		StrategoGame game = aNode.getState();

		if (isTerminal(game)) {
			return true;
		}

		return false;
	}

	public int getScoreValue(StrategoGame state) {
		if (isTerminal(state)) {
			// TODO Return high score for winner
		}
		ArrayList<StrategoPiece> playerNorthPieces = state.getPlayerNorth().getInGamePieces();
		ArrayList<StrategoPiece> playerSouthPieces = state.getPlayerSouth().getInGamePieces();
		int tottalNorthStr = calculateTottalStr(playerNorthPieces);
		int tottalSouthStr = calculateTottalStr(playerSouthPieces);
		if (tottalNorthStr > tottalSouthStr) {
			return 1;
		}
		if (tottalNorthStr < tottalSouthStr) {
			return -1;
		}


		return 0;
	}


	public boolean isTerminal(StrategoGame aState) {
		if (!checkForFlag(aState.getPlayerNorth().getInGamePieces())) {
			return true;
		}
		if (!checkForFlag(aState.getPlayerSouth().getInGamePieces())) {
			return true;
		}
		// TODO non-movement conditions

		return false;

	}

	public boolean isAttackAction(StrategoGame state, StrategoAbstractEvent action) {
		// TODO
		return false;
	}

	private boolean checkForFlag(ArrayList<StrategoPiece> pieceList) {
		for (int i = 0; i < pieceList.size(); i++) {
			if (pieceList.get(i).getPieceType() == PieceType.FLAG) {
				return true;
			}
		}
		return false;
	}

	private int calculateTottalStr(ArrayList<StrategoPiece> pieceList) {
		int sum=0;
		for (int i = 0; i < pieceList.size(); i++) {
			sum = sum + PieceHierarchyData.pieceLvlMap.get(pieceList.get(i).getPieceType());
			// TODO fix conditions
		}
		return sum;
	}

	@Override
	public int getScoreValue(TreeNode<StrategoGame, ?> aNode, int referance) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getScoreValue(StrategoGame state, int referance) {
		// TODO Review
		return getScoreValue(state);
	}

}
