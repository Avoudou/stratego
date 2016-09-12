package gameSystems;

import java.util.ArrayList;

import abstractGameComponents.StrategoGame;
import events.DeploymentEvent;
import events.StrategoAbstractEvent;
import gameObjects.PieceType;
import gameObjects.StrategoBoardTile;
import gameObjects.StrategoPiece;
import gameObjects.TerrainType;

public class DeploymentSystem {




	public void proccessDeployEvent(StrategoGame aGame, StrategoAbstractEvent anEvent) {
		ArrayList<StrategoPiece> tempList;
		DeploymentEvent trueEvent = (DeploymentEvent) anEvent;
		tempList = setActivePlayerList(aGame);
			
		if (unitAvailiableForDeploy(tempList, trueEvent.getPieceType())) {
			if (!checkDeployZone(aGame, trueEvent)) {
				return;
			}

			StrategoPiece tempPiece = removeDeployedPiece(aGame.getRuntimeData().getActivePlayer()
					.getUnDeployedPieces(),
					trueEvent.getPieceType());
				
			placePieceOnBoard(aGame, tempPiece, trueEvent, tempList);
			StrategoPiece printPiece = aGame.getBoard().getBoardStracture()[trueEvent.getyCoord()][trueEvent.getxCoord()]
					.getOccupyingPiece();

			System.out.println(printPiece + "   deployed");
			System.out.println("id of deployment tile"
					+ aGame.getBoard().getBoardStracture()[trueEvent.getyCoord()][trueEvent.getxCoord()]);
			System.out.println("event Coords Deploy : " + trueEvent.getxCoord() + "  " + trueEvent.getyCoord());

		} else {
			System.out.println("Unit unavailiable");
			}



	}

	public void autoDeployArmy(StrategoGame aGame, StrategoAbstractEvent anEvent) {

		ArrayList<StrategoPiece> deployList = aGame.getRuntimeData().getActivePlayer().getUnDeployedPieces();
		System.out.println(deployList.size());

		if (aGame.getRuntimeData().getActivePlayer() == aGame.getPlayerNorth()) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 10; j++) {
					System.out.println(deployList.size());
					int tempIndex = (int) (deployList.size() * Math.random());
					System.out.println("tempIndex : " + tempIndex);
					StrategoPiece randomPiece = deployList.get(tempIndex);
					deployList.remove(tempIndex);
					System.out.println("new ListSize : " + deployList.size());
					randomPiece.setxPos(j);
					randomPiece.setyPos(i);
					System.out.println(i + " " + j);
					aGame.getBoard().getBoardStracture()[i][j].setOccupyingPiece(randomPiece);

				}
			}
		} else {
			for (int i = 6; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					int tempIndex = (int) (deployList.size() * Math.random());
					StrategoPiece randomPiece = deployList.get(tempIndex);
					deployList.remove(tempIndex);
					randomPiece.setxPos(j);
					randomPiece.setyPos(i);
					aGame.getBoard().getBoardStracture()[i][j].setOccupyingPiece(randomPiece);

				}
			}

		}

	}

	private ArrayList<StrategoPiece> setActivePlayerList(StrategoGame aGame) {

		return aGame.getRuntimeData().getActivePlayer().getUnDeployedPieces();
	}

	private void placePieceOnBoard(StrategoGame aGame, StrategoPiece aPiece, DeploymentEvent event,
			ArrayList<StrategoPiece> availiablePieces) {
		if(checkIfTileAvailiable(aGame, event)){
			aPiece.setxPos(event.getxCoord());
			aPiece.setyPos(event.getyCoord());
			aGame.getBoard().getBoardStracture()[event.getyCoord()][event.getxCoord()].setOccupyingPiece(aPiece);

			System.out.println(aGame.getBoard().getBoardStracture()[event.getyCoord()][event.getxCoord()].getOccupyingPiece()
					+ " unit occuping give tile");
			System.out.println(aPiece.getPieceType() + "  was placed");
			
		}else{
			availiablePieces.add(aPiece);
			System.out.println("False Placement");
		}

	}

	private boolean checkIfTileAvailiable(StrategoGame aGame,DeploymentEvent event) {
		StrategoBoardTile tempTile = aGame.getBoard().getBoardStracture()[event.getyCoord()][event.getxCoord()];
		if (tempTile.getTerrainType() == TerrainType.LAKE) {
			System.out.println("Lake NO ACCESS");
			return false;
		}
		if (tempTile.getOccupyingPiece() == null) {
			// System.out.println("Valide placement");
			return true;

		}
		System.out.println("Another unit already exists");

		return false;
	}



	private boolean unitAvailiableForDeploy(ArrayList<StrategoPiece> availiablePieces, PieceType pieceType) {
		for (int i = 0; i < availiablePieces.size(); i++) {
			if (availiablePieces.get(i).getPieceType() == pieceType) {
				return true;
			}
		}
		return false;
	}

	private StrategoPiece removeDeployedPiece(ArrayList<StrategoPiece> availiablePieces, PieceType pieceType) {
		for (int i = 0; i < availiablePieces.size(); i++) {
			if (availiablePieces.get(i).getPieceType() == pieceType) {
				StrategoPiece tempPiece = availiablePieces.get(i);
				availiablePieces.remove(i);
				return tempPiece;
			}
		}
		return null;

	}

	private boolean checkDeployZone(StrategoGame aGame, DeploymentEvent event) {
		int x = event.getxCoord();
		int y = event.getyCoord();
		if (aGame.getRuntimeData().getActivePlayer() == aGame.getPlayerNorth()) {
			boolean b = (y >= 0) && (x >= 0) && (x <= aGame.getBoard().getBoardStracture()[0].length - 1) && (y <= 3);
			System.out.println("valid deploy Place : " + b);
			return b;
		}
		if (aGame.getRuntimeData().getActivePlayer() == aGame.getPlayerSouth()) {
			boolean b = (y >= 6) && (x >= 0) && (x <= aGame.getBoard().getBoardStracture()[0].length - 1) && (y <= 9);
			System.out.println("valid deploy Place : " + b);
			return b;
		}
		System.out.println("illegal deployment place");
		return false;
	}

}
