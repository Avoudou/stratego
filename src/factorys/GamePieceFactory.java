package factorys;

import gameObjects.StrategoPiece;

import java.util.ArrayList;

import abstractGameComponents.PieceHierarchyData;

public class GamePieceFactory {


	private final int[] pieceQuantityList = { 6, 1, 1, 2, 3, 4, 4, 4, 5, 8, 1, 1 };

	public ArrayList<StrategoPiece> createPlayerPieces() {

		ArrayList<StrategoPiece> pieceList = new ArrayList<StrategoPiece>();
		for (int i = 0; i < pieceQuantityList.length; i++) {
			for (int j = 0; j < pieceQuantityList[i]; j++) {
				StrategoPiece aPiece = new StrategoPiece(PieceHierarchyData.pieceTypeMap.get(pieceQuantityList.length
						- i - 1));
				pieceList.add(aPiece);
				
			}
		}

		return pieceList;
	}

}
