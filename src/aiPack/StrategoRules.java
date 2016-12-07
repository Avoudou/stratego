package aiPack;

import events.StrategoAbstractEvent;
import gameLogic.SystemsManager;
import abstractDefinitions.TreeNode;
import abstractGameComponents.StrategoGame;
import abstractSearchComponents.Rules;

public class StrategoRules extends Rules<StrategoGame> {

	private SystemsManager systemManager;

	public StrategoRules(SystemsManager systemManager){
		this.systemManager = systemManager;
	}

	@Override
	public boolean isTerminal(TreeNode<StrategoGame, ?> aNode) {
		if (systemManager.getCheckVictorySystem().checkIfOpponentFlagCaptured(aNode.getState())) {
			return true;
		}
		// // TODO : implement the other conditions
		// if (false) {
		// return true;
		// }
		//
		// if (false) {
		// return true;
		// }
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
