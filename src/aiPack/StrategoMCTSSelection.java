package aiPack;

import java.util.ArrayList;

import abstractDefinitions.TreeNode;
import abstractGameComponents.StrategoGame;
import abstractSearchComponents.Selection;
import events.StrategoAbstractEvent;

public class StrategoMCTSSelection extends Selection<StrategoGame, StrategoAbstractEvent> {

	public TreeNode<StrategoGame, StrategoAbstractEvent> selectChild(TreeNode<StrategoGame, StrategoAbstractEvent> aNode) {

		ArrayList<TreeNode<StrategoGame, StrategoAbstractEvent>> childrenList = aNode.getChildrenList();

		if (hasUnexploredChild(childrenList)) {
			for (int k = 0; k < childrenList.size(); k++) {
				if (childrenList.get(k).getGamesPlayed() == 0) {
					return childrenList.get(k);
				}

			}

		}

		// uct attempt
		TreeNode<StrategoGame, StrategoAbstractEvent> tempNode = null;
		double selectionReferance = 0;
		int tottalWins = 1;

		for (int k = 0; k < childrenList.size(); k++) {

			tottalWins = tottalWins + childrenList.get(k).getGamesWon();
			if (tottalWins > 1) {
				tottalWins = tottalWins - 1;
			}
		}

		for (int i = 0; i < childrenList.size(); i++) {
			TreeNode<StrategoGame, StrategoAbstractEvent> examinedNode = childrenList.get(i);

			double selectionValue = 1.0 * examinedNode.getGamesWon() / examinedNode.getGamesPlayed() + 1.2
					* (Math.sqrt(Math.log(tottalWins) / examinedNode.getGamesPlayed()));

			// double selectionValue = 1.0 * examinedNode.getGamesWon() / examinedNode.getGamesPlayed() + Math.sqrt(2)
			// * (Math.sqrt(Math.log(tottalWins) / examinedNode.getGamesPlayed()));

			if (selectionValue == Math.max(selectionValue, selectionReferance)) {
				selectionReferance = selectionValue;
				tempNode = examinedNode;

			}

		}

		return tempNode;
	}

	private boolean hasUnexploredChild(ArrayList<TreeNode<StrategoGame, StrategoAbstractEvent>> childrenList) {
		for (int k = 0; k < childrenList.size(); k++) {
			if (childrenList.get(k).getGamesPlayed() == 0) {
				return true;
			}

		}

		return false;
	}

}
