package aiPack;

import java.util.ArrayList;

import abstractGameComponents.StrategoGame;
import abstractSearchComponents.MoveGenerator;
import abstractSearchComponents.Playthrough;
import abstractSearchComponents.Rules;
import events.StrategoAbstractEvent;

public class StrategoPlaythrough extends Playthrough<StrategoGame, StrategoAbstractEvent> {

	private MoveGenerator<StrategoGame, StrategoAbstractEvent> nodeGenerator;
	private Rules<StrategoGame> rules;

	public StrategoPlaythrough(MoveGenerator<StrategoGame, StrategoAbstractEvent> nodeGenerator,
			Rules<StrategoGame> rules) {
		super(nodeGenerator, rules);
		this.nodeGenerator = nodeGenerator;
		this.rules = rules;
	}



	public int returnStrategoPlaythroughResult(StrategoGame state) {
		state = (StrategoGame) state.deepCopySelf();

		// tempNode.setPlaythoughNode(true);

		// TreeNode<StrategoGame, StrategoAbstractEvent> rootNode = leafNode.getRootNode();
		// int referance = rootNode.getState().getActivePlayer();
		int movesCount = 0;
		while (!rules.isTerminal(state) && movesCount < 2) {
			state = (StrategoGame) state.deepCopySelf();

			ArrayList<StrategoAbstractEvent> childrenNodes = nodeGenerator.generateAvailiableMoves(state);
			nodeGenerator.applyAction(state, childrenNodes.get((int) (childrenNodes.size() * Math.random())));
			movesCount++;

		}
		return rules.getScoreValue(state, 0);
	}

}
