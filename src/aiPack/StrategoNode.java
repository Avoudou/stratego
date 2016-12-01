package aiPack;

import abstractDefinitions.TreeNode;
import abstractGameComponents.StrategoGame;
import events.StrategoAbstractEvent;

public class StrategoNode extends TreeNode<StrategoGame, StrategoAbstractEvent> {

	private boolean isAttackNode;

	private StrategoNode[] outcomeNodeList = new StrategoNode[3];

	public StrategoNode(StrategoGame rootState) {
		super(rootState);

	}

	public StrategoNode[] getOutcomeNodeList() {
		return outcomeNodeList;
	}

	public void setOutcomeNodeList(StrategoNode[] outcomeNodeList) {
		this.outcomeNodeList = outcomeNodeList;
	}

	public boolean isAttackNode() {
		return isAttackNode;
	}

	public void setAttackNode(boolean isAttackNode) {
		this.isAttackNode = isAttackNode;
	}

}
