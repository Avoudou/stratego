package project.stratego.ai.mcts.aiPack;

import java.util.ArrayList;

import project.stratego.ai.mcts.abstractDefinitions.TreeNode;
import project.stratego.ai.mcts.abstractGameComponents.StrategoGame;
import project.stratego.ai.mcts.abstractSearchComponents.MCTSPerformer;
import project.stratego.ai.mcts.abstractSearchComponents.MoveGenerator;
import project.stratego.ai.mcts.abstractSearchComponents.Rules;
import project.stratego.ai.mcts.events.StrategoAbstractEvent;
import project.stratego.ai.mcts.gameObjects.StrategoPiece;

public class StrategoMCTSPerformer extends MCTSPerformer<StrategoGame, StrategoAbstractEvent> {

	private StrategoPlaythrough playthrough;
	private int numberOfObservations = 1;

	public StrategoMCTSPerformer(Rules<StrategoGame> rules,
			MoveGenerator<StrategoGame, StrategoAbstractEvent> moveGenerator, StrategoPlaythrough playthrough) {
		super(rules, moveGenerator, playthrough);
		this.playthrough = playthrough;
	}


	public TreeNode<StrategoGame, StrategoAbstractEvent> runMCTSMultiObvs(
			TreeNode<StrategoGame, StrategoAbstractEvent> rootNode) {


		ArrayList<TreeNode<StrategoGame, StrategoAbstractEvent>> simList = new ArrayList<TreeNode<StrategoGame, StrategoAbstractEvent>>();

		for (int i = 0; i < numberOfObservations; i++) {
			TreeNode<StrategoGame, StrategoAbstractEvent> tempNode = deepCopyNodeANDRandomize(rootNode);
			// System.out.println(tempNode.getState().getBoard().getBoardStracture()[0][0].getOccupyingPiece().getPieceType());
			// System.out.println(tempNode.getState().getBoard().getBoardStracture()[9][9].getOccupyingPiece()
			// .getPieceType());

			mctsItteration(tempNode);
			simList.add(makeDummyNode(tempNode));

			for (int j = 0; j < noOfItterations; j++) {




			}

		}

		// System.out.println("test  randomization : "
		// + simList.get(0).getState().getBoard().getBoardStracture()[0][0].getOccupyingPiece().getPieceType());
		// System.out.println("test  randomization : "
		// + simList.get(0).getState().getBoard().getBoardStracture()[9][9].getOccupyingPiece().getPieceType());
		ArrayList<StrategoAbstractEvent> moveList = moveGenerator.generateAvailiableMoves(rootNode.getState());
		return getBestChild(simList, moveList);

	}

	private TreeNode<StrategoGame, StrategoAbstractEvent> deepCopyNode(
			TreeNode<StrategoGame, StrategoAbstractEvent> aNode) {

		StrategoGame copyState = (StrategoGame) aNode.getState().deepCopySelf();
		copyState.fixPiecePlacement(copyState);
		TreeNode<StrategoGame, StrategoAbstractEvent> copy = new TreeNode<StrategoGame, StrategoAbstractEvent>(
				copyState);

		return copy;
	}

	private TreeNode<StrategoGame, StrategoAbstractEvent> makeDummyNode(
			TreeNode<StrategoGame, StrategoAbstractEvent> tempNode) {
		TreeNode<StrategoGame, StrategoAbstractEvent> returnNode = new TreeNode<StrategoGame, StrategoAbstractEvent>(
				null);
		returnNode.setGamesPlayed(tempNode.getGamesPlayed());
		returnNode.setGamesWon(tempNode.getGamesWon());
		ArrayList<TreeNode<StrategoGame, StrategoAbstractEvent>> dummyChilds = new ArrayList<TreeNode<StrategoGame, StrategoAbstractEvent>>();
		for (int i = 0; i < tempNode.getChildrenList().size(); i++) {
			TreeNode<StrategoGame, StrategoAbstractEvent> childTemp = new TreeNode<StrategoGame, StrategoAbstractEvent>(
					null);
			childTemp.setGamesPlayed(tempNode.getGamesPlayed());
			childTemp.setGamesWon(tempNode.getGamesPlayed());
			dummyChilds.add(childTemp);
		}
		returnNode.setChildrenList(dummyChilds);

		return returnNode;
	}

	private TreeNode<StrategoGame, StrategoAbstractEvent> deepCopyNodeANDRandomize(
			TreeNode<StrategoGame, StrategoAbstractEvent> aNode) {

		StrategoGame copyState = schuffleRootCopy((StrategoGame) aNode.getState());
		copyState.fixPiecePlacement(copyState);
		TreeNode<StrategoGame, StrategoAbstractEvent> copy = new TreeNode<StrategoGame, StrategoAbstractEvent>(
				copyState);


		return copy;
	}

	private TreeNode<StrategoGame, StrategoAbstractEvent> getBestChild(
			ArrayList<TreeNode<StrategoGame, StrategoAbstractEvent>> simList, ArrayList<StrategoAbstractEvent> moveList) {

		ArrayList<ArrayList<TreeNode<StrategoGame, StrategoAbstractEvent>>> valuesMatrix = new ArrayList<ArrayList<TreeNode<StrategoGame, StrategoAbstractEvent>>>();
		for (int i = 0; i < simList.size(); i++) {
			valuesMatrix.add(simList.get(i).getChildrenList());

		}
		return getBestChild(simList.get(0));
	}
	@Override
	public void mctsItteration(TreeNode<StrategoGame, StrategoAbstractEvent> rootNode) {
		StrategoGame initGame = (StrategoGame) rootNode.getState().deepCopySelf();

		double scoreMultiplier = (initGame.getActivePlayer() == 1) ? 1.0 : -1.0;
		// int rootPieces = rootNode.getState().getPlayerNorth().getInGamePieces().size()
		// + rootNode.getState().getPlayerSouth().getInGamePieces().size();

		TreeNode<StrategoGame, StrategoAbstractEvent> visititedNode = rootNode;

		// visititedNode.setState(schuffleRootCopy(initGame));

    while (!rules.isTerminal(visititedNode.getState()) && !checkIfLeafNode(visititedNode)) {
			TreeNode<StrategoGame, StrategoAbstractEvent> selectChild = selection.selectChild(visititedNode);
      if (selectChild == null) {
        break;
      } else {
        visititedNode = selectChild;
      }
		}
		// Logger.println(" selection result: " + " depth " + visititedNode.getNodeDepth());


		if (rules.isTerminal(visititedNode)) {
			double result = playthrough.returnStrategoPlaythroughResult(visititedNode.getState());
			result *= scoreMultiplier;
			updateTree(visititedNode, result);
			return;
		}
		ArrayList<StrategoAbstractEvent> moves = moveGenerator.generateAvailiableMoves(visititedNode.getState());

		addChildNodes(visititedNode, moves);
		visititedNode = selection.selectChild(visititedNode);
		double result = playthrough.returnStrategoPlaythroughResult(visititedNode.getState());
		result *= scoreMultiplier;

		updateTree(visititedNode, result);


	}

	@Override
	public void addChildNodes(TreeNode<StrategoGame, StrategoAbstractEvent> aNode,
			ArrayList<StrategoAbstractEvent> moves) {

		for (int i = 0; i < moves.size(); i++) {
			@SuppressWarnings("unchecked")
			StrategoGame newState = (StrategoGame) aNode.getState().deepCopySelf();
			moveGenerator.applyAction(newState, moves.get(i));
			TreeNode<StrategoGame, StrategoAbstractEvent> newNode = new TreeNode<StrategoGame, StrategoAbstractEvent>(
					newState);
			newNode.setParent(aNode);
			aNode.getChildrenList().add(newNode);
			newNode.setNodeDepth(aNode.getNodeDepth() + 1);
			newNode.setAction(moves.get(i));
			// newNode.setPlaythoughNode(aNode.isPlaythoughNode());

		}
	}

	private StrategoGame schuffleRootCopy(StrategoGame initGame) {
		System.out.println("shuffIe");
		ArrayList<StrategoPiece> opponentsPieces = initGame.getActiveOpponentObj().getInGamePieces();
		ArrayList<StrategoPiece> opponentsPiecesNew = new ArrayList<StrategoPiece>();

		ArrayList<StrategoPiece> unKnownPieces = new ArrayList<StrategoPiece>();
		ArrayList<StrategoPiece> unKnownPiecesNew = new ArrayList<StrategoPiece>();

		ArrayList<StrategoPiece> revealedPiecesOpponent = initGame.getActiveOpponentObj().getPiecesKnownByOpponent();
		// ArrayList<StrategoPiece> revealedPiecesOpponentNew = new ArrayList<StrategoPiece>();

		for (int i = 0; i < opponentsPieces.size(); i++) {
			if (!checkIfPieceInList(revealedPiecesOpponent, opponentsPieces.get(i))) {
				unKnownPieces.add(opponentsPieces.get(i));
			}
		}
		// PIace known pieces
		for (int i = 0; i < revealedPiecesOpponent.size(); i++) {
			StrategoPiece tempPiece = revealedPiecesOpponent.get(i);
			StrategoPiece tempPieceNew = tempPiece.copyPiece();
			opponentsPiecesNew.add(tempPieceNew);
		}
		// PLace unknown pieces
		ArrayList<StrategoPiece> dummyList = new ArrayList<StrategoPiece>(unKnownPieces);
		System.out.println("dummy size " + dummyList.size());
		for (int i = 0; i < unKnownPieces.size(); i++) {
			// System.out.println("  " + i);
			int index = (int) (Math.random() * dummyList.size());
			System.out.println(" random index " + index);
			StrategoPiece tempPiece = dummyList.get(index);
			dummyList.remove(index);
			// System.out.println(tempPiece.getPieceType());
			StrategoPiece tempPieceNew = new StrategoPiece(tempPiece.getPieceType());
			tempPieceNew.setxPos(unKnownPieces.get(i).getxPos());
			tempPieceNew.setyPos(unKnownPieces.get(i).getyPos());
			opponentsPiecesNew.add(tempPieceNew);
			unKnownPiecesNew.add(tempPieceNew);
		}


		StrategoGame shuffledState = new StrategoGame(initGame);
		shuffledState.getActiveOpponentObj().setInGamePieces(opponentsPiecesNew);
		// shuffledState.getActiveOpponentObj().s
		shuffledState.fixPiecePlacement(shuffledState);



		return shuffledState;
	}

	private boolean checkIfPieceInList(ArrayList<StrategoPiece> list, StrategoPiece piece) {
		for (int i = 0; i < list.size(); i++) {
			if (piece.getPieceID() == list.get(i).getPieceID()) {
				return true;
			}
		}
		return false;
	}
	
}
