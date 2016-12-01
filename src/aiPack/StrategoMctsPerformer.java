package aiPack;

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

}
