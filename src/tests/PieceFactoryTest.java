package tests;

import java.util.ArrayList;

import project.stratego.ai.mcts.factories.GamePieceFactory;
import project.stratego.ai.mcts.gameObjects.StrategoPiece;

public class PieceFactoryTest {

	public static void main(String[] args) {
		GamePieceFactory pieceFactory = new GamePieceFactory();

		ArrayList<StrategoPiece> pieceList = pieceFactory.createPlayerPieces();

		System.out.println(pieceList.size());
		for (int i = 0; i < pieceList.size(); i++) {
			System.out.println(pieceList.get(i).getPieceType());
		}
	}

}
