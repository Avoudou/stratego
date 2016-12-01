package aiPack;

import abstractDefinitions.TreeNode;
import abstractGameComponents.StrategoGame;
import abstractSearchComponents.Rules;
import events.StrategoAbstractEvent;

public class StrategoRules extends Rules<StrategoGame> {

	@Override
	public boolean isTerminal(TreeNode<StrategoGame, ?> aNode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTerminal(StrategoGame aState) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getScoreValue(TreeNode<StrategoGame, ?> aNode, int referance) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getScoreValue(StrategoGame state, int referance) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isAttackAction(StrategoGame state, StrategoAbstractEvent action) {
		return false;
	}

}
