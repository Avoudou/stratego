package aiPack;

import gameObjects.PieceType;

import java.util.HashMap;

public class StrategoEvaluationValues {
	public static final HashMap<PieceType, Integer> pieceValues = new HashMap<PieceType, Integer>();
	static {
		pieceValues.put(PieceType.BOMB, 6);
		pieceValues.put(PieceType.MARSHAL, 12);
		pieceValues.put(PieceType.GENERAL, 10);
		pieceValues.put(PieceType.COLONEL, 9);
		pieceValues.put(PieceType.MAJOR, 8);
		pieceValues.put(PieceType.CAPTAIN, 7);
		pieceValues.put(PieceType.LIUTENANT, 6);
		pieceValues.put(PieceType.SEARGENT, 5);
		pieceValues.put(PieceType.MINER, 4);
		pieceValues.put(PieceType.SCOUT, 1);
		pieceValues.put(PieceType.SPY, 6);
		pieceValues.put(PieceType.FLAG, 100);

	}


}
