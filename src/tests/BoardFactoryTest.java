package tests;

import project.stratego.ai.mcts.factories.BoardFactory;
import project.stratego.ai.mcts.gameObjects.StrategoBoard;

public class BoardFactoryTest {

	public static void main(String[] args) {
		BoardFactory boardFactory = new BoardFactory();

		StrategoBoard board = boardFactory.createBoard();

		for (int i = 0; i < board.getBoardStracture().length; i++) {
			System.out.println();
			for (int j = 0; j < board.getBoardStracture()[0].length; j++) {
				System.out.print(board.getBoardStracture()[i][j].getTerrainType() + " ");

			}

		}

	}

}
