package aiPack;

import java.util.ArrayList;

import abstractDefinitions.TreeNode;
import abstractGameComponents.StrategoGame;
import abstractSearchComponents.MCTSperformer;
import abstractSearchComponents.MoveGenerator;
import abstractSearchComponents.Rules;
import events.StrategoAbstractEvent;

public class StrategoMctsPerformer extends MCTSperformer<StrategoGame, StrategoAbstractEvent> {

	public StrategoMctsPerformer(Rules<StrategoGame> rules,
			MoveGenerator<StrategoGame, StrategoAbstractEvent> moveGenerator) {
		super(rules, moveGenerator);

	}

	@Override
	public void mctsItteration(TreeNode<StrategoGame, StrategoAbstractEvent> rootNode) {

		TreeNode<StrategoGame, StrategoAbstractEvent> visititedNode = rootNode;

		while (!checkIfLeafNode(visititedNode)) {
			visititedNode = selection.selectChild(visititedNode);

		}


		if (rules.isTerminal(visititedNode)) {
			int result = playthrough.returnPlaythroughResult(visititedNode);
			updateTree(visititedNode, result);
			return;
		}
		ArrayList<StrategoAbstractEvent> moves = moveGenerator.generateAvailiableMoves(visititedNode.getState());

		addChildNodes(visititedNode, moves);
		visititedNode = selection.selectChild(visititedNode);
		int result = playthrough.returnPlaythroughResult(visititedNode);

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
	
}
